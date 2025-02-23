package org.mertguler.cinemium.service.cinema;

import jakarta.validation.Valid;
import org.mertguler.cinemium.exception.model.ResourceAlreadyExistException;
import org.mertguler.cinemium.payload.dto.CinemaDTO;
import org.mertguler.cinemium.payload.response.CinemaResponse;

public interface CinemaService {
    CinemaResponse getAllCinemas();

    CinemaDTO createCinema(@Valid CinemaDTO cinemaDTO);

    CinemaDTO updateCinema(@Valid CinemaDTO cinemaDTO, Long cinemaId);
}
