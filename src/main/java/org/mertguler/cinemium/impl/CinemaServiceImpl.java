package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.ResourceNotFoundException;
import org.mertguler.cinemium.mapper.CustomMapper;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.building.CinemaTranslation;
import org.mertguler.cinemium.model.core.Address;
import org.mertguler.cinemium.model.core.CinemaImage;
import org.mertguler.cinemium.payload.dto.AddressDTO;
import org.mertguler.cinemium.payload.dto.CinemaDTO;
import org.mertguler.cinemium.payload.response.CinemaResponse;
import org.mertguler.cinemium.repository.AddressRepository;
import org.mertguler.cinemium.repository.CinemaImageRepository;
import org.mertguler.cinemium.repository.CinemaRepository;
import org.mertguler.cinemium.repository.CinemaTranslationRepository;
import org.mertguler.cinemium.service.CinemaService;
import org.mertguler.cinemium.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CustomMapper mapper;
    @Autowired
    private CinemaTranslationRepository cinemaTranslationRepository;
    @Autowired
    private CinemaImageRepository cinemaImageRepository;

    @Autowired
    private FileService fileService;

    @Value("${project.image.cinema}")
    private String imagesPath;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public CinemaResponse getAllCinemas(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String city, String language) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Cinema> pageCinemas;

        if (city != null) {
            pageCinemas = cinemaRepository.findAllByCity(pageDetails, city);
        } else {
            pageCinemas = cinemaRepository.findAll(pageDetails);
        }

        List<Cinema> cinemas = pageCinemas.getContent();

        if (!(language.equals("en"))) {
            for (Cinema cinema : cinemas) {
                CinemaTranslation cinemaTranslation = cinemaTranslationRepository
                        .findByCinemaCinemaIdAndLanguage(cinema.getCinemaId(), language);

                if (cinemaTranslation == null) {
                    continue;
                }

                String name = cinemaTranslation.getName();

                if (name == null) {
                    continue;
                }

                if (name.isEmpty() || name.isBlank()) {
                    continue;
                }

                cinema.setName(name);

                String summary = cinemaTranslation.getSummary();

                if (summary == null) {
                    continue;
                }

                if (summary.isEmpty() || summary.isBlank()) {
                    continue;
                }

                cinema.setSummary(summary);
            }
        }

        for (Cinema cinema: cinemas){
            CinemaImage posterImage = cinemaImageRepository.findCinemaImageByCinemaCinemaIdAndImageType(cinema.getCinemaId(), 0, Limit.of(1));
            if (posterImage == null){
                continue;
            }

            String posterPath = posterImage.getFilePath();

            if (posterPath == null){
                continue;
            }

            cinema.setPosterPath(posterPath);
        }

        List<CinemaDTO> cinemaDTOS = cinemas.stream()
                .map(cinema -> mapper.toCinemaDto(cinema))
                .toList();

        CinemaResponse cinemaResponse = new CinemaResponse();
        cinemaResponse.setContent(cinemaDTOS);
        cinemaResponse.setPageNumber(pageCinemas.getNumber());
        cinemaResponse.setPageSize(pageCinemas.getSize());
        cinemaResponse.setTotalElements(pageCinemas.getTotalElements());
        cinemaResponse.setTotalPages(pageCinemas.getTotalPages());
        cinemaResponse.setLastPage(pageCinemas.isLast());
        return cinemaResponse;
    }

    @Override
    public CinemaDTO createCinema(CinemaDTO cinemaDTO){
        Cinema cinema = mapper.toCinema(cinemaDTO);
        String cinemaId = cinema.getCinemaId();
        Cinema cinemaFromDb = cinemaRepository.findCinemaByCinemaId(cinemaId);

        if (cinemaFromDb != null) {
            throw new ResourceAlreadyExistException("Cinema", "cinemaId", cinemaId);
        }

        Cinema savedCinema = cinemaRepository.save(cinema);
        return mapper.toCinemaDto(savedCinema);
    }

    @Override
    public CinemaDTO updateCinema(CinemaDTO cinemaDTO, String cinemaId) {
        Cinema savedCinema = cinemaRepository.findCinemaByCinemaId(cinemaId);

        if (savedCinema == null){
            throw new ResourceNotFoundException("Cinema", "cinemaId", String.valueOf(cinemaId));
        }
        Cinema cinema = mapper.toCinema(cinemaDTO);
        cinema.setCinemaId(cinemaId);
        savedCinema = cinemaRepository.save(cinema);
        return mapper.toCinemaDto(savedCinema);
    }

    @Override
    public CinemaDTO deleteCinema(String cinemaId) {
        Cinema cinemaFromDb = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));

        cinemaRepository.delete(cinemaFromDb);
        return mapper.toCinemaDto(cinemaFromDb);
    }

    @Override
    public CinemaDTO getCinema(String cinemaId) {
        Cinema cinema = cinemaRepository.findCinemaByCinemaId(cinemaId);

        if (cinema == null){
            throw new ResourceNotFoundException("Cinema", "cinemaId", cinemaId);
        }

        return mapper.toCinemaDto(cinema);
    }


    @Override
    public CinemaDTO updateCinemaPoster(String cinemaId, MultipartFile image) throws IOException {
        Cinema cinemaFromDb = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));

        String fileName = fileService.uploadImage(imagesPath, image);

        cinemaFromDb.setPosterPath(fileName);

        Cinema updatedCinema = cinemaRepository.save(cinemaFromDb);
        return mapper.toCinemaDto(updatedCinema);
    }

    @Override
    public AddressDTO createAddress(String cinemaId, AddressDTO addressDTO) {
        Cinema cinema = cinemaRepository.findById(cinemaId).
                orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));

        Address oldAddress = cinema.getAddressInfo();

        Address address = mapper.toAddressModel(addressDTO);
        cinema.setAddressInfo(address);
        addressRepository.save(address);
        cinemaRepository.save(cinema);

        if (oldAddress != null){
            addressRepository.delete(oldAddress);
        }

        return addressDTO;
    }

    @Override
    public AddressDTO deleteAddress(String cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).
                orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));

        Address address = cinema.getAddressInfo();
        cinema.setAddressInfo(null);

        if (address != null){
            addressRepository.delete(address);
        } else {
            throw new ResourceNotFoundException("Address", "cinemaId", cinemaId);
        }

        cinemaRepository.save(cinema);

        return mapper.toAddressDto(address);
    }

}
