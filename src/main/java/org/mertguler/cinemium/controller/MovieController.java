package org.mertguler.cinemium.controller;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.MovieDTO;
import org.mertguler.cinemium.payload.response.MovieResponse;
import org.mertguler.cinemium.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/public/movies")
    public ResponseEntity<MovieResponse> getAllMovies(){
        MovieResponse movieResponse = movieService.getAllMovies();
        return new ResponseEntity<>(movieResponse, HttpStatus.OK);
    }

    @PostMapping("/public/movies")
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO){
        MovieDTO savedMovieDTO = movieService.createMovie(movieDTO);
        return new ResponseEntity<>(savedMovieDTO, HttpStatus.CREATED);
    }

    @PutMapping("/public/movies/{movieId}")
    public ResponseEntity<MovieDTO> updateMovie(@Valid @RequestBody MovieDTO movieDTO, @PathVariable String movieId){
        MovieDTO savedMovieDTO = movieService.updateMovie(movieDTO, movieId);
        return new ResponseEntity<>(savedMovieDTO, HttpStatus.OK);
    }

    @DeleteMapping("/public/movies/{movieId}")
    public ResponseEntity<MovieDTO> deleteMovie(@PathVariable String movieId){
        MovieDTO deletedMovieDTO = movieService.deleteMovie(movieId);
        return new ResponseEntity<>(deletedMovieDTO, HttpStatus.OK);
    }

    @PutMapping("/public/movies/{movieId}/image")
    public ResponseEntity<MovieDTO> updateMovieSmallPoster(@PathVariable String movieId,
                                                         @RequestParam("image") MultipartFile image) throws IOException {
        MovieDTO updatedMovie = movieService.updateMoviePoster(movieId, image);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }
}
