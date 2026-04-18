package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateRequestDto {

    private String name;
    private String description;

}
