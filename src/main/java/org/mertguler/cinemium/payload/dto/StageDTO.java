package org.mertguler.cinemium.payload.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StageDTO {
    private Long stageId;

    @NotBlank
    private String name;

    private Long cinemaId;
}
