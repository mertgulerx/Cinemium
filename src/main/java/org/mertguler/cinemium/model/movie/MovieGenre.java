package org.mertguler.cinemium.model.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.validator.EnumValidator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieGenre {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "genre_id")
//    private Long genreId;

    @Id
    @EnumValidator(enumClass = Genre.class)
    @Column(name = "genre_name")
    private String genreName;
}
