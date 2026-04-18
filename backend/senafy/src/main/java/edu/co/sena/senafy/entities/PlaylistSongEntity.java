package edu.co.sena.senafy.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "playlist_song")
public class PlaylistSongEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "playlist_id")
    private Integer playlistId;

    @Column(name = "song_id")
    private Integer songId;

}
