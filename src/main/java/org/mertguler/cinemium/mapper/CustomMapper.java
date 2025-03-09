package org.mertguler.cinemium.mapper;

import org.mapstruct.Mapping;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.building.Seat;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.model.core.Address;
import org.mertguler.cinemium.model.movie.Genre;
import org.mertguler.cinemium.model.movie.Movie;
import org.mertguler.cinemium.payload.dto.*;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CustomMapper {
    @Mapping(target = "addressInfo", ignore = true)
    @Mapping(target = "translations", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "stages", ignore = true)
    Cinema toCinema(CinemaDTO cinemaDTO);
    @Mapping(target = "address", source = "addressInfo.address")
    CinemaDTO toCinemaDto(Cinema cinema);

    Genre toGenre(GenreDTO movieGenreDTO);
    GenreDTO toGenreDto(Genre genre);

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


    @Mapping(target = "id", ignore = true)
    Address toAddressModel(AddressDTO addressDTO);
    AddressDTO toAddressDto(Address address);



}
