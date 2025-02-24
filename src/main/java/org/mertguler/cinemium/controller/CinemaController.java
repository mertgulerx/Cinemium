package org.mertguler.cinemium.controller;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.CinemaDTO;
import org.mertguler.cinemium.payload.response.CinemaResponse;
import org.mertguler.cinemium.service.cinema.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/public/cinemas")
    public ResponseEntity<CinemaResponse> getAllCinemas(){
        CinemaResponse cinemaResponse = cinemaService.getAllCinemas();
        return new ResponseEntity<>(cinemaResponse, HttpStatus.OK);
    }

    @PostMapping("/public/cinemas")
    public ResponseEntity<CinemaDTO> createCinema(@Valid @RequestBody CinemaDTO cinemaDTO){
        CinemaDTO savedCinemaDTO = cinemaService.createCinema(cinemaDTO);
        return new ResponseEntity<>(savedCinemaDTO, HttpStatus.OK);
    }

    @PutMapping("/public/cinemas/{cinemaId}")
    public ResponseEntity<CinemaDTO> updateCinema(@Valid @RequestBody CinemaDTO cinemaDTO, @PathVariable Long cinemaId){
        CinemaDTO savedCinemaDTO = cinemaService.updateCinema(cinemaDTO, cinemaId);
        return new ResponseEntity<>(savedCinemaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/public/cinemas/{cinemaId}")
    public ResponseEntity<CinemaDTO> deleteCinema(@PathVariable Long cinemaId){
        CinemaDTO deletedCinemaDTO = cinemaService.deleteCinema(cinemaId);
        return new ResponseEntity<>(deletedCinemaDTO, HttpStatus.OK);
    }


}
