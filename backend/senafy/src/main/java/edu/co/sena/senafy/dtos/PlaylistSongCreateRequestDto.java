package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSongCreateRequestDto {

    private Integer playlistId;
    private Integer songId;

}
