package org.mertguler.cinemium.model.session;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.model.movie.Movie;

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
    private Movie movie;

    @ManyToOne
    private Stage stage;

    @Min(0)
    private BigDecimal regularPrice;

    @Min(0)
    private BigDecimal studentPrice;

    @Enumerated(EnumType.STRING)
    private LanguageFormat languageFormat;

    @Enumerated(EnumType.STRING)
    private MovieDimension movieDimension;

    private LocalDateTime startingDate;

    private LocalDateTime endingDate;

}
