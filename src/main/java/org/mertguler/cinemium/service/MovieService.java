package org.mertguler.cinemium.service;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.MovieDTO;
import org.mertguler.cinemium.payload.response.MovieResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MovieService {
    MovieResponse getAllMovies();

    MovieDTO createMovie(@Valid MovieDTO movieDTO);

    MovieDTO updateMovie(MovieDTO movieDTO, String movieId);

    MovieDTO deleteMovie(String movieId);

    MovieDTO updateMoviePoster(String movieId, MultipartFile image) throws IOException;
}
