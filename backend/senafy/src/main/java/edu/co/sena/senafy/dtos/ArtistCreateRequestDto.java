package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistCreateRequestDto {

    private String name;
    private String country;
    private String biography;

}
