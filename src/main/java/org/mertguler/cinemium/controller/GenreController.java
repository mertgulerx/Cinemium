package org.mertguler.cinemium.controller;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.MovieGenreDTO;
import org.mertguler.cinemium.service.MovieGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GenreController {

    @Autowired
    private MovieGenreService movieGenreService;

    @PostMapping("/public/genres")
    public ResponseEntity<MovieGenreDTO> createGenre(@Valid @RequestBody MovieGenreDTO movieGenreDTO){
        MovieGenreDTO savedMovieGenreDTO = movieGenreService.createMovieGenre(movieGenreDTO);
        return new ResponseEntity<>(savedMovieGenreDTO, HttpStatus.OK);
    }
}
