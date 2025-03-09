package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.building.CinemaTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaTranslationRepository extends JpaRepository<CinemaTranslation, String> {
    CinemaTranslation findByCinemaCinemaIdAndLanguage(String cinemaId, String language);
}
