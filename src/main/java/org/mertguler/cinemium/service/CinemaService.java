package org.mertguler.cinemium.service;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.AddressDTO;
import org.mertguler.cinemium.payload.dto.CinemaDTO;
import org.mertguler.cinemium.payload.response.CinemaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CinemaService {
    CinemaResponse getAllCinemas(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String city, String language);

    CinemaDTO createCinema(@Valid CinemaDTO cinemaDTO);

    CinemaDTO updateCinema(CinemaDTO cinemaDTO, String cinemaId);

    CinemaDTO deleteCinema(String cinemaId);

    CinemaDTO getCinema(String cinemaId);

    CinemaDTO updateCinemaPoster(String cinemaId, MultipartFile image) throws IOException;

    AddressDTO createAddress(String cinemaId, AddressDTO addressDTO);

    AddressDTO deleteAddress(String cinemaId);
}
