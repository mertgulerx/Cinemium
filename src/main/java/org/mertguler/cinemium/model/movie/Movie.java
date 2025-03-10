package org.mertguler.cinemium.model.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.core.MovieImage;
import org.mertguler.cinemium.model.session.Session;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    private String movieId;

    private String title;

    @Column(columnDefinition = "TEXT", length = 512)
    private String summary;

    private Integer length;

    private String trailer;

    private String poster;

    private Float imdbScore;

    private Float rtScore;

    private String releaseStatus;

    @OneToMany(mappedBy = "movie",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<MovieImage> images = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_name"))
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movie",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

    @OneToMany(mappedBy = "movie",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<MovieTranslation> translations = new ArrayList<>();
}
