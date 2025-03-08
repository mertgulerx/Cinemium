package org.mertguler.cinemium.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.building.Cinema;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinema_images")
public class CinemaImage extends Image{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageId;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
}
