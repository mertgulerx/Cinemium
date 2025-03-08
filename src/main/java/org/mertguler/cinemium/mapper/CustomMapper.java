package org.mertguler.cinemium.mapper;

import org.mapstruct.Mapping;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.model.building.seat.Seat;
import org.mertguler.cinemium.model.movie.Movie;
import org.mertguler.cinemium.model.movie.MovieGenre;
import org.mertguler.cinemium.payload.dto.*;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CustomMapper {
    @Mapping(target = "stages", ignore = true)
    Cinema toCinema(CinemaDTO cinemaDTO);
    CinemaDTO toCinemaDto(Cinema cinema);

    MovieGenre toGenre(MovieGenreDTO movieGenreDTO);
    MovieGenreDTO toGenreDto(MovieGenre movieGenre);

    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Movie toMovie(MovieDTO movieDTO);
    MovieDTO toMovieDto(Movie movie);

    @Mapping(target = "stage", ignore = true)
    @Mapping(target = "coupleId", ignore = true)
    Seat toSeat(SeatDTO seatDTO);
    @Mapping(target = "stageId", ignore = true)
    SeatDTO toSeatDto(Seat seat);


    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "cinema", ignore = true)
    Stage toStage(StageDTO stageDTO);
    @Mapping(target = "cinemaId", ignore = true)
    StageDTO toStageDto(Stage stage);



}
