package org.mertguler.cinemium.model.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageType {
    POSTER(0),
    BACKDROP(1),
    GENERAL(2);

    private final int id;
}
