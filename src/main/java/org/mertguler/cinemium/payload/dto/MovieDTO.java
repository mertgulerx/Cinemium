package org.mertguler.cinemium.payload.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.movie.ReleaseStatus;
import org.mertguler.cinemium.util.validator.EnumValidator;

import java.util.UUID;

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
