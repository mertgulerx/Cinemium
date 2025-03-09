package org.mertguler.cinemium.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.mertguler.cinemium.config.AppConstants;
import org.mertguler.cinemium.payload.dto.CinemaDTO;
import org.mertguler.cinemium.payload.response.CinemaResponse;
import org.mertguler.cinemium.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/cinemas")
    public ResponseEntity<CinemaResponse> getAllCinemas(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                        @Max(50) @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                        @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CINEMAS_BY, required = false) String sortBy,
                                                        @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder,
                                                        @RequestParam(name = "city", required = false) String city,
                                                        @RequestParam(name = "language", defaultValue = "en", required = false) String language){
        CinemaResponse cinemaResponse = cinemaService.getAllCinemas(pageNumber, pageSize, sortBy, sortOrder, city, language);
        return new ResponseEntity<>(cinemaResponse, HttpStatus.OK);
    }


    @GetMapping("/cinemas/{cinemaId}")
    public ResponseEntity<CinemaDTO> getCinema(@PathVariable String cinemaId){
        CinemaDTO cinemaDTO = cinemaService.getCinema(cinemaId);
        return new ResponseEntity<>(cinemaDTO, HttpStatus.OK);
    }

    @PostMapping("/cinemas")
    public ResponseEntity<CinemaDTO> createCinema(@Valid @RequestBody CinemaDTO cinemaDTO){
        CinemaDTO savedCinemaDTO = cinemaService.createCinema(cinemaDTO);
        return new ResponseEntity<>(savedCinemaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/cinemas/{cinemaId}")
    public ResponseEntity<CinemaDTO> updateCinema(@Valid @RequestBody CinemaDTO cinemaDTO, @PathVariable String cinemaId){
        CinemaDTO savedCinemaDTO = cinemaService.updateCinema(cinemaDTO, cinemaId);
        return new ResponseEntity<>(savedCinemaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/cinemas/{cinemaId}")
    public ResponseEntity<CinemaDTO> deleteCinema(@PathVariable String cinemaId){
        CinemaDTO deletedCinemaDTO = cinemaService.deleteCinema(cinemaId);
        return new ResponseEntity<>(deletedCinemaDTO, HttpStatus.OK);
    }


}
