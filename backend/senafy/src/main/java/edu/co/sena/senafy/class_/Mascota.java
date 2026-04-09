package edu.co.sena.senafy.class_;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {

    private String identificacion;
    private String nombre;
    private String raza;
    private String color;

}
