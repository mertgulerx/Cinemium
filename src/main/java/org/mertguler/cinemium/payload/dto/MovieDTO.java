package org.mertguler.cinemium.payload.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.movie.ReleaseStatus;
import org.mertguler.cinemium.validator.EnumValidator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Long movieId;

    @NotBlank
    private String title;

    private String summary;

    /**
     * Minute
     */
    @Min(0)
    private Integer length;

    private String smallPoster;

    private String largePoster;

    private String trailerLink;

    private Float imdbScore;

    private Float rtScore;

    @EnumValidator(enumClass = ReleaseStatus.class)
    private String releaseStatus;
}
