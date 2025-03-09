package org.mertguler.cinemium.service;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.GenreDTO;

public interface GenreService {
    GenreDTO createMovieGenre(@Valid GenreDTO genreDTO);
}
