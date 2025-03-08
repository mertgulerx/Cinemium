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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @NotBlank
    private String title;

    private String summary;

    /**
     * Minute
     */
    @Min(0)
    private Integer length;

    private String smallPoster;

    private String largePoster;

    private String trailer;

    private Float imdbScore;

    private Float rtScore;

    @EnumValidator(enumClass = ReleaseStatus.class)
    private String releaseStatus;

    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_name"))
    private List<MovieGenre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movie",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();
}
