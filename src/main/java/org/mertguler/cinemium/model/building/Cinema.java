package org.mertguler.cinemium.model.building;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long cinemaId;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String code;

    @ToString.Exclude
    @OneToMany
    @JoinTable(name = "cinema_stages", joinColumns = @JoinColumn(name = "cinema_id"),
            inverseJoinColumns = @JoinColumn(name = "stage_id"))
    private List<Stage> stages = new ArrayList<>();
}
