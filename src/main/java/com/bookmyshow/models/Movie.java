package com.bookmyshow.models;

import com.bookmyshow.enums.MovieLanguage;
import com.bookmyshow.enums.MovieStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movies")
public class Movie extends GlobalRecord {

    @Column(nullable = false, unique = true)
    private String movieCode;

    @Column(nullable = false)
    private String title;

    private String originalTitle;

    @Column(length = 2000)
    private String synopsis;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovieLanguage language;

    private String genre; // Action, Comedy, Drama

    private Integer durationMinutes;

    private LocalDate releaseDate;

    private String censorCertificate; // U, UA, A

    private String director;

    private String producer;

    private String castMembers; // comma separated or JSON later

    private String musicDirector;

    private String productionHouse;

    private String country;

    private String trailerUrl;

    private String posterUrl;

    private String bannerUrl;

    private Double imdbRating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovieStatus status;

    @Column(nullable = false)
    private boolean active = true;
}
