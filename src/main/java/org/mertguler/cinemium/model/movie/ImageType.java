package org.mertguler.cinemium.model.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageType {
    SMALL_POSTER(0),
    LARGE_POSTER(1),
    GENERAL(2);

    private final int id;
}
