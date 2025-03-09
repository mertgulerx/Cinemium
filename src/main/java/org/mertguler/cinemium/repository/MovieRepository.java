package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findMovieByMovieId(String movieId);
}
