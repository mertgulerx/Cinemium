package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.core.CinemaImage;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CinemaImageRepository extends JpaRepository<CinemaImage, UUID> {
    CinemaImage findCinemaImageByCinemaCinemaIdAndImageType(String cinemaId, int i, Limit of);
}
