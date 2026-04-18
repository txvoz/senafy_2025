package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongResponseDto {

    private Integer id;
    private Integer artistId;
    private Integer categoryId;
    private String title;
    private String duration;
    private String audioUrl;
    private java.time.LocalDate releaseDate;
    private Integer views;

}
