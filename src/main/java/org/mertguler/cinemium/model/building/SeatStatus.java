package org.mertguler.cinemium.model.building;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatStatus {
    AVAILABLE(0),
    PENDING(1),
    BOOKED(2),
    FAULTY(3);

    private final int id;

}
