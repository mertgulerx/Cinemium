package org.mertguler.cinemium.model.session;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.model.movie.Movie;
import org.mertguler.cinemium.validator.EnumValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

}
