package org.mertguler.cinemium.repository;

import org.mertguler.cinemium.model.building.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String> {;
    @Query("SELECT c FROM Cinema c WHERE c.addressInfo.city = :city")
    Page<Cinema> findAllByCity(Pageable pageDetails, String city);

    Cinema findCinemaByCinemaId(String cinemaId);
}
