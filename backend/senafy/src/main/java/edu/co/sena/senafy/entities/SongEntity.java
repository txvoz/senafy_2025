package edu.co.sena.senafy.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "song")
public class SongEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "artist_id")
    private Integer artistId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private String duration;

    @Column(name = "audio_url")
    private String audioUrl;

    @Column(name = "release_date")
    private java.time.LocalDate releaseDate;

    @Column(name = "views")
    private Integer views;

}
