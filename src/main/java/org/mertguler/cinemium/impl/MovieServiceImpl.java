package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.ResourceNotFoundException;
import org.mertguler.cinemium.model.movie.Movie;
import org.mertguler.cinemium.payload.dto.MovieDTO;
import org.mertguler.cinemium.payload.response.MovieResponse;
import org.mertguler.cinemium.repository.MovieRepository;
import org.mertguler.cinemium.service.FileService;
import org.mertguler.cinemium.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String imagesPath;

    @Override
    public MovieResponse getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieDTO> movieDTOS = movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .toList();

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setContent(movieDTOS);
        return movieResponse;
    }

    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        Long movieId = movie.getMovieId();
        Movie movieFromDb = movieRepository.findMovieByMovieId(movieId);

        if (movieFromDb != null) {
            throw new ResourceAlreadyExistException("Movie", "movieId", movieId);
        }

        Movie savedMovie = movieRepository.save(movie);
        return modelMapper.map(savedMovie, MovieDTO.class);
    }

    @Override
    public MovieDTO updateMovie(MovieDTO movieDTO, Long movieId) {
        Movie savedMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "movieId", movieId));

        Movie movie = modelMapper.map(movieDTO, Movie.class);
        movie.setMovieId(movieId);
        savedMovie = movieRepository.save(movie);
        return modelMapper.map(savedMovie, MovieDTO.class);
    }

    @Override
    public MovieDTO deleteMovie(Long movieId) {
        Movie movieFromDb = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "movieId", movieId));

        movieRepository.delete(movieFromDb);
        return modelMapper.map(movieFromDb, MovieDTO.class);
    }

    @Override
    public MovieDTO updateMovieSmallPoster(Long movieId, MultipartFile image) throws IOException {
        Movie movieFromDb = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "movieId", movieId));

        String fileName = fileService.uploadImage(imagesPath, image);

        movieFromDb.setSmallPoster(fileName);

        Movie updatedMovie = movieRepository.save(movieFromDb);
        return modelMapper.map(updatedMovie, MovieDTO.class);
    }
}
