package org.mertguler.cinemium.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.building.Cinema;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinema_images")
public class CinemaImage{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageId;

    private String filePath;

    private Integer imageType;

    private Double aspectRatio;

    private Integer height;

    private Integer width;

    private String iso6391;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
}
