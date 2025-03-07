package org.mertguler.cinemium.model.building.seat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.util.validator.EnumValidator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Min(0)
    @NotNull
    private Integer rowIndex;

    @Min(0)
    @NotNull
    private Integer columnIndex;

    @EnumValidator(enumClass = SeatType.class)
    private String type;

    @EnumValidator(enumClass = SeatStatus.class)
    private String status;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;
    
    private Long coupleId;

}
