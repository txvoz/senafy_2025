package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MascotaCreateRequestDto {

    @NonNull
    private String identificacion;

    @NonNull
    private String nombre;

    @NonNull
    private String raza;

    private String color;

}
