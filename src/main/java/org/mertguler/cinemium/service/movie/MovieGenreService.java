package org.mertguler.cinemium.service.movie;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.MovieGenreDTO;
import org.springframework.stereotype.Service;

public interface MovieGenreService {
    MovieGenreDTO createMovieGenre(@Valid MovieGenreDTO movieGenreDTO);
}
