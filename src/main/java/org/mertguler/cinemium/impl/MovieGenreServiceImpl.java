package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.mapper.CustomMapper;
import org.mertguler.cinemium.model.movie.MovieGenre;
import org.mertguler.cinemium.payload.dto.MovieGenreDTO;
import org.mertguler.cinemium.repository.GenreRepository;
import org.mertguler.cinemium.service.MovieGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieGenreServiceImpl implements MovieGenreService {

    @Autowired
    private CustomMapper mapper;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public MovieGenreDTO createMovieGenre(MovieGenreDTO movieGenreDTO) {
        MovieGenre movieGenre = mapper.toGenre(movieGenreDTO);
        String genreName = movieGenre.getName();

        if(genreRepository.existsById(genreName)){
            throw new ResourceAlreadyExistException("Genre", "genreName", genreName);
        }

        return mapper.toGenreDto(movieGenre);
    }
}
