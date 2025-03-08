package org.mertguler.cinemium.model.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Image {
    private String filePath;

    private Integer imageType;

    private Double aspectRatio;

    private Integer height;

    private Integer width;

    private String iso6391;
}
