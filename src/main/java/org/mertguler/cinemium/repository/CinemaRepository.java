package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.building.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {;
    Page<Cinema> findAllByCity(Pageable pageDetails, String city);

    Cinema findCinemaByCinemaId(UUID cinemaId);
}
