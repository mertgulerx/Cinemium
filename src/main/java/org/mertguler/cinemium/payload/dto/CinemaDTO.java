package org.mertguler.cinemium.payload.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDTO {
    private Long cinemaId;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String code;

    private String poster;

}
