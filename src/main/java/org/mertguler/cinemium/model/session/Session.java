package org.mertguler.cinemium.model.session;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.model.building.seat.Seat;
import org.mertguler.cinemium.model.movie.Movie;
import org.mertguler.cinemium.validator.EnumValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @Min(0)
    private BigDecimal regularPrice;

    @Min(0)
    private BigDecimal studentPrice;

    @EnumValidator(enumClass = LanguageFormat.class)
    private String languageFormat;

    @EnumValidator(enumClass = MovieDimension.class)
    private String movieDimension;

    private LocalDateTime startingDate;

    private LocalDateTime endingDate;

    @ToString.Exclude
    @OneToMany( cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    @JoinTable(name = "sessions_seats",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seats = new ArrayList<>();
}
