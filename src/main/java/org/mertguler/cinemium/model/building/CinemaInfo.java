package org.mertguler.cinemium.model.building;

import jakarta.persistence.*;
import lombok.*;
import org.mertguler.cinemium.model.core.City;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinema_infos")
public class CinemaInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String address;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String summary;
}
