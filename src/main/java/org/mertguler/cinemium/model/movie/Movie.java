package org.mertguler.cinemium.model.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.session.Session;
import org.mertguler.cinemium.util.validator.EnumValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID movieId;

    private String title;

    @Column(columnDefinition = "TEXT", length = 512)
    private String summary;

    private Integer length;

    private String trailer;

    private Float imdbScore;

    private Float rtScore;

    private String releaseStatus;

    @OneToMany(mappedBy = "movie",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<MovieImage> images = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_name"))
    private List<MovieGenre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movie",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();
}
