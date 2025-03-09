package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.building.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    @Query("SELECT stage FROM Stage stage WHERE stage.cinema.cinemaId = ?1")
    Optional<List<Stage>> findAllByCinemaId(Long cinemaId);

    @Query("SELECT stage FROM Stage stage WHERE stage.cinema.cinemaId = ?1 AND stage.name = ?2")
    Stage findStageByCinemaIdAndName(String cinemaId, String name);

    Stage findByStageId(String stageId);
}
