package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.ResourceNotFoundException;
import org.mertguler.cinemium.mapper.CustomMapper;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.building.CinemaTranslation;
import org.mertguler.cinemium.payload.dto.CinemaDTO;
import org.mertguler.cinemium.payload.response.CinemaResponse;
import org.mertguler.cinemium.repository.CinemaImageRepository;
import org.mertguler.cinemium.repository.CinemaRepository;
import org.mertguler.cinemium.repository.CinemaTranslationRepository;
import org.mertguler.cinemium.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

        cinemas.forEach(cinema -> cinema
                .setPoster(
                        cinemaImageRepository.findCinemaImageByCinemaCinemaIdAndImageType(cinema.getCinemaId(), 0, Limit.of(1)).getFilePath()));

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
        UUID cinemaId = cinema.getCinemaId();
        Cinema cinemaFromDb = cinemaRepository.findCinemaByCinemaId(cinemaId);

        if (cinemaFromDb != null) {
            throw new ResourceAlreadyExistException("Cinema", "cinemaId", String.valueOf(cinemaId));
        }

        Cinema savedCinema = cinemaRepository.save(cinema);
        return mapper.toCinemaDto(savedCinema);
    }

    @Override
    public CinemaDTO updateCinema(CinemaDTO cinemaDTO, UUID cinemaId) {
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
    public CinemaDTO deleteCinema(Long cinemaId) {
        Cinema cinemaFromDb = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));

        cinemaRepository.delete(cinemaFromDb);
        return mapper.toCinemaDto(cinemaFromDb);
    }


}
