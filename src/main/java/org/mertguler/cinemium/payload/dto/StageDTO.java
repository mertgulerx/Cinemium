package org.mertguler.cinemium.payload.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StageDTO {
    private UUID stageId;

    @NotBlank
    private String name;

    private Long cinemaId;
}
