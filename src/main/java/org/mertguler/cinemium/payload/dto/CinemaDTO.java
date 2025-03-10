package org.mertguler.cinemium.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDTO {
    private String cinemaId;

    private String name;

    private String address;

    private String summary;

    private String posterPath;
}
