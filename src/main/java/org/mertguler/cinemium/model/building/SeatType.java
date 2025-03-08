package org.mertguler.cinemium.model.building;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatType {
    REGULAR(0),
    COUPLE(1),
    ACCESSIBLE(2);

    private final int id;
}
