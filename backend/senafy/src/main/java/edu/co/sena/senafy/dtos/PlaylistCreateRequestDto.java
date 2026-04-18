package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistCreateRequestDto {

    private Integer userId;
    private String name;
    private java.time.LocalDateTime creationDate;

}
