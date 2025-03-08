package org.mertguler.cinemium.model.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie_translations")
public class MovieTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID translationId;

    private String title;

    @Column(columnDefinition = "TEXT", length = 512)
    private String summary;

    private String language;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
