package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.building.CinemaTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CinemaTranslationRepository extends JpaRepository<CinemaTranslation, UUID> {
    CinemaTranslation findByCinemaCinemaIdAndLanguage(UUID cinemaId, String language);
}
