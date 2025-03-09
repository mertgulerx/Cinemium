package org.mertguler.cinemium.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private String movieId;

    private String title;

    private String summary;

    private Integer length;

    private String smallPoster;

    private String largePoster;

    private String trailer;

    private Float imdbScore;

    private Float rtScore;

    private String releaseStatus;
}
