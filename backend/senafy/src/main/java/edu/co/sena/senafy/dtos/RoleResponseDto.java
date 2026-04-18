package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {

    private Integer id;
    private String name;
    private String description;

}
