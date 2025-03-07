package org.mertguler.cinemium.service;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.MovieGenreDTO;

public interface MovieGenreService {
    MovieGenreDTO createMovieGenre(@Valid MovieGenreDTO movieGenreDTO);
}
