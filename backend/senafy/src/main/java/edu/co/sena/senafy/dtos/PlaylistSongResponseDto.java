package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSongResponseDto {

    private Integer id;
    private Integer playlistId;
    private Integer songId;

}
