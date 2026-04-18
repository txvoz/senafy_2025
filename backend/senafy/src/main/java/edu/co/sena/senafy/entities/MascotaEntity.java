package edu.co.sena.senafy.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mascota")
public class MascotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "raza")
    private String raza;

    @Column(name = "color")
    private String color;

}
