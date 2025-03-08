package org.mertguler.cinemium.controller;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.SeatDTO;
import org.mertguler.cinemium.payload.response.SeatResponse;
import org.mertguler.cinemium.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/public/cinemas/stages/{stageId}/seats")
    public ResponseEntity<SeatResponse> getSeats(@PathVariable Long stageId){
        SeatResponse seatResponse = seatService.getSeats(stageId);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @PostMapping("/public/cinemas/stages/{stageId}/seats")
    public ResponseEntity<SeatDTO> createSeat(@Valid @RequestBody SeatDTO seatDTO,
                                               @PathVariable Long stageId){
        SeatDTO savedSeatDTO = seatService.createSeat(seatDTO, stageId);
        return new ResponseEntity<>(savedSeatDTO, HttpStatus.OK);
    }

    @PutMapping("/public/cinemas/stages/seats/{seatId}")
    public ResponseEntity<SeatDTO> updateSeat(@Valid @RequestBody SeatDTO seatDTO,
                                                @PathVariable Long seatId){
        SeatDTO savedSeatDTO = seatService.updateSeat(seatDTO, seatId);
        return new ResponseEntity<>(savedSeatDTO, HttpStatus.OK);
    }

    @PostMapping("/public/cinemas/stages/{stageId}/seats/couple")
    public ResponseEntity<SeatResponse> createCoupleSeats(@PathVariable Long stageId,
                                                   @RequestBody SeatResponse seatList){
        SeatDTO firstSeatDTO = seatList.getContent().get(0);
        SeatDTO secondSeatDTO = seatList.getContent().get(1);
        SeatResponse seatResponse = seatService.createCoupleSeats(stageId, firstSeatDTO, secondSeatDTO);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @DeleteMapping("/public/cinemas/stages/seats/{seatId}")
    public ResponseEntity<SeatDTO> deleteSeat(@PathVariable Long seatId){
        SeatDTO deletedSeatDTO = seatService.deleteSeat(seatId);
        return new ResponseEntity<>(deletedSeatDTO, HttpStatus.OK);
    }

}
