package org.mertguler.cinemium.service.seat;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.SeatDTO;
import org.mertguler.cinemium.payload.response.SeatResponse;

public interface SeatService {
    SeatResponse getSeats(Long stageId);

    SeatDTO createSeat(@Valid SeatDTO seatDTO, Long stageId);

    SeatDTO updateSeat(@Valid SeatDTO seatDTO, Long seatId);

    SeatDTO deleteSeat(Long seatId);

    SeatResponse makeSeatsCouple(Long firstSeatId, Long secondSeatId);
}
