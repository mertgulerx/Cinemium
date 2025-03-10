package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.building.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT seat FROM Seat seat WHERE seat.stage.stageId = ?1")
    Optional<List<Seat>> findAllByStageId(String stageId);

    @Query("SELECT seat FROM Seat seat WHERE seat.stage.stageId = ?1 AND seat.rowIndex = ?2 AND seat.columnIndex = ?3")
    Seat findSeatByStageIdAndPosition(String stageId, Integer rowIndex, Integer columnIndex);

    Seat findBySeatId(UUID seatId);
}
