package org.mertguler.cinemium.model.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReleaseStatus {
    RELEASED(0),
    PRESALE(1),
    UPCOMING(2);

    private final int id;
}
