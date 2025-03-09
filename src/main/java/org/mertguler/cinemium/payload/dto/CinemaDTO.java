package org.mertguler.cinemium.payload.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.core.City;

import java.util.UUID;

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
