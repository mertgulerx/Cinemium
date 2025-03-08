package org.mertguler.cinemium.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.payload.dto.MovieGenreDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieGenreResponse {
    List<MovieGenreDTO> content;
}
