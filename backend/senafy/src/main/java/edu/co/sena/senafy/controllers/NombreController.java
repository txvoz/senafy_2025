package edu.co.sena.senafy.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/nombre")
public class NombreController {

    private ArrayList<String> nombres = new ArrayList<>();


    @PostMapping("/{nombre}")
    public String crear(@PathVariable("nombre") String nombre){

        System.out.println("El nombre que llego es " + nombre);

        boolean isAdded = false;

        if(!nombre.isEmpty()) {
            this.nombres.add(nombre);
            isAdded = true;
        }

        return isAdded ? "Nombre agregado " + nombre + " la cantidad de nombres es " + nombres.size() : "Nombre no agregado";
    }

    /*
    @GetMapping()
    public String listar(){
        return "";
    }

    @PutMapping()
    public String editar(){
        return "";
    }

    @DeleteMapping
    public String eliminar(){
        return "";
    }*/

}
