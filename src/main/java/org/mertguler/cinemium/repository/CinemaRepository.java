package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.building.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {;
    Cinema findCinemaByCinemaId(Long cinemaId);
}
