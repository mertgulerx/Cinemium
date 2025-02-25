package org.mertguler.cinemium.service.movie;

import org.mertguler.cinemium.exception.model.ResourceAlreadyExistException;
import org.mertguler.cinemium.model.movie.MovieGenre;
import org.mertguler.cinemium.payload.dto.MovieGenreDTO;
import org.mertguler.cinemium.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieGenreServiceImpl implements MovieGenreService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public MovieGenreDTO createMovieGenre(MovieGenreDTO movieGenreDTO) {
        MovieGenre movieGenre = modelMapper.map(movieGenreDTO, MovieGenre.class);
        String genreName = movieGenre.getGenreName();

        if(genreRepository.existsById(genreName)){
            throw new ResourceAlreadyExistException("Genre", "genreName", genreName);
        }

        return modelMapper.map(genreRepository.save(movieGenre), MovieGenreDTO.class);
    }
}
