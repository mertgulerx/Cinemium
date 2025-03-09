package org.mertguler.cinemium.model.building;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.core.CinemaImage;
import org.mertguler.cinemium.model.core.City;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cinemas")
public class Cinema {
    @Id
    @Column(name = "cinema_id")
    private String cinemaId;

    private String name;

    private String address;

    private String summary;

    private String posterPath;

    @OneToOne
    @JoinColumn(name = "city_name")
    private City city;

    @OneToMany(mappedBy = "cinema",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Stage> stages = new ArrayList<>();

    @OneToMany(mappedBy = "cinema",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<CinemaImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "cinema",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<CinemaTranslation> translations = new ArrayList<>();
}
