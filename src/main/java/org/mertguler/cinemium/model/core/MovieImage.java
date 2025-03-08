package org.mertguler.cinemium.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.movie.Movie;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie_images")
public class MovieImage extends Image{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
