package org.mertguler.cinemium.model.building;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.mertguler.cinemium.model.building.seat.Seat;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stageId;

    @NotBlank
    private String name;

    @ManyToOne()
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ToString.Exclude
    @OneToMany(mappedBy = "stage",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();
}
