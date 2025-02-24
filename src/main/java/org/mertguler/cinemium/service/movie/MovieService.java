package org.mertguler.cinemium.service.movie;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.MovieDTO;
import org.mertguler.cinemium.payload.response.MovieResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MovieService {
    MovieResponse getAllMovies();

    MovieDTO createMovie(@Valid MovieDTO movieDTO);

    MovieDTO updateMovie(@Valid MovieDTO movieDTO, Long movieId);

    MovieDTO deleteMovie(Long movieId);

    MovieDTO updateMovieSmallPoster(Long movieId, MultipartFile image) throws IOException;
}
