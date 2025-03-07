package org.mertguler.cinemium.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.movie.Genre;
import org.mertguler.cinemium.validator.EnumValidator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieGenreDTO {
    @EnumValidator(enumClass = Genre.class)
    private String name;
}
