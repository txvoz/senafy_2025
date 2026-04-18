package edu.co.sena.senafy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MascotaResponseDto {

    private Long id;

    private String identificacion;

    private String nombre;

    private String raza;

    private String color;

}
