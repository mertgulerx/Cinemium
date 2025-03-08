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
@Table(name = "cinema_translations")
public class CinemaTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "translation_id")
    private UUID translationId;

    private String name;

    private String summary;

    private String language;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
}
