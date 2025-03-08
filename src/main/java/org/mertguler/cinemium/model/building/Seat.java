package org.mertguler.cinemium.model.building;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID seatId;

    private Integer rowIndex;

    private Integer columnIndex;

    private Integer type;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;
    
    private Long coupleId;
}
