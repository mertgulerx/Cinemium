package org.mertguler.cinemium.payload.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.building.SeatStatus;
import org.mertguler.cinemium.model.building.SeatType;
import org.mertguler.cinemium.util.validator.EnumValidator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    private UUID seatId;

    @NotNull
    @Min(0)
    private Integer rowIndex;

    @NotNull
    @Min(0)
    private Integer columnIndex;

    //     "type": "must be any of enum class org.mertguler.cinemium.model.building.SeatType"
    @EnumValidator(enumClass = SeatType.class)
    private String type;

    @EnumValidator(enumClass = SeatStatus.class)
    private String status;

    private Long stageId;
}
