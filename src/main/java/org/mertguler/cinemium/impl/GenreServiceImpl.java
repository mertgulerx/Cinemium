package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.mapper.CustomMapper;
import org.mertguler.cinemium.model.movie.Genre;
import org.mertguler.cinemium.payload.dto.GenreDTO;
import org.mertguler.cinemium.repository.GenreRepository;
import org.mertguler.cinemium.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private CustomMapper mapper;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public GenreDTO createMovieGenre(GenreDTO genreDTO) {
        Genre genre = mapper.toGenre(genreDTO);
        String genreName = genre.getName();

        if(genreRepository.existsById(genreName)){
            throw new ResourceAlreadyExistException("Genre", "genreName", genreName);
        }

        return mapper.toGenreDto(genre);
    }
}
