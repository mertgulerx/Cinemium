package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.ResourceNotFoundException;
import org.mertguler.cinemium.mapper.CustomMapper;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.payload.dto.CinemaDTO;
import org.mertguler.cinemium.payload.response.CinemaResponse;
import org.mertguler.cinemium.repository.CinemaRepository;
import org.mertguler.cinemium.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CustomMapper mapper;

    @Override
    public CinemaResponse getAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();

        List<CinemaDTO> cinemaDTOS = cinemas.stream()
                .map(cinema -> mapper.toCinemaDto(cinema))
                .toList();

        CinemaResponse cinemaResponse = new CinemaResponse();
        cinemaResponse.setContent(cinemaDTOS);
        return cinemaResponse;
    }

    @Override
    public CinemaDTO createCinema(CinemaDTO cinemaDTO){
        Cinema cinema = mapper.toCinema(cinemaDTO);
        Long cinemaId = cinema.getCinemaId();
        Cinema cinemaFromDb = cinemaRepository.findCinemaByCinemaId(cinemaId);

        if (cinemaFromDb != null) {
            throw new ResourceAlreadyExistException("Cinema", "cinemaId", cinemaId);
        }

        Cinema savedCinema = cinemaRepository.save(cinema);
        return mapper.toCinemaDto(savedCinema);
    }

    @Override
    public CinemaDTO updateCinema(CinemaDTO cinemaDTO, Long cinemaId) {
        Cinema savedCinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));

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
