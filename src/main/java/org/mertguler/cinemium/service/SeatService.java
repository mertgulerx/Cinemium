package org.mertguler.cinemium.service;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.SeatDTO;
import org.mertguler.cinemium.payload.response.SeatResponse;

import java.util.UUID;

public interface SeatService {
    SeatResponse getSeats(String stageId);

    SeatDTO createSeat(@Valid SeatDTO seatDTO, String stageId);

    SeatDTO updateSeat(SeatDTO seatDTO, UUID seatId);

    SeatDTO deleteSeat(UUID seatId);

    SeatResponse createCoupleSeats(String stageId, SeatDTO firstSeatDTO, SeatDTO secondSeatDTO);
}
