package edu.co.sena.senafy.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/nombre")
public class NombreController {

    private ArrayList<String> nombres = new ArrayList<>();

    private String validateIndex(int index) {
        if(this.nombres.isEmpty()) {
            return "En este momento no hay registros";
        }

        if(index < 1) {
            return "El indice ingresado esta fuera de rango, debe ser superior a cero";
        }

        if(index > this.nombres.size()) {
            return "El indice buscado no existe";
        }

        return "";
    }
    private String validateNombre(String nombre) {
        if(nombre.matches(".*\\d.*")) {
            return "El nombre tiene digitos, no es valido!";
        }

        if(nombre.matches(".*[^a-zA-Z0-9 ].*")) {
            return "El nombre tiene caracteres especiales, no es valido!";
        }

        if(nombre.length() < 3) {
            return "El nombre contiene menos de 3 caracteres, el minimo es 3!";
        }

        if(nombre.length() > 10) {
            return "El nombre contiene mas de 10 caracteres, el maximo es 10!";
        }

        return "";
    }

    @PostMapping("/{nombre}")
    public String crear(@PathVariable("nombre") String nombre){

        String returnValidateNombre = validateNombre(nombre);
        if(!returnValidateNombre.isEmpty()) {
            return returnValidateNombre;
        }

        this.nombres.add(nombre);

        return "Nombre agregado " + nombre + " la cantidad de nombres es " + nombres.size();
    }

    @GetMapping()
    public String listar(@RequestParam(name = "query", required = false) String query){

        System.out.println("El parametro " + query);

        //nombres.size() == 0
        if(nombres.isEmpty()) {
            return "<h1 style='color:red'>No hay nombres aun!</h1>";
        }

        String nombresConcatenados = "";
        for (int i = 0; i < nombres.size(); i++) {
            if(Objects.isNull(query)) {
                nombresConcatenados += (i+1) + " :: " + nombres.get(i) + "<br>";
            } else {
                if(nombres.get(i).contains(query)) {
                    nombresConcatenados += (i+1) + " :: " + nombres.get(i) + "<br>";
                }
            }
        }
        return nombresConcatenados;
    }

    @GetMapping("/{index}")
    public String detalle(@PathVariable("index") int index){

        String returnValidateIndex = validateIndex(index);
        if(!returnValidateIndex.isEmpty()) {
            return returnValidateIndex;
        }

        int indexBuscar = index - 1;
        String nombre = this.nombres.get(indexBuscar);

        return "El nombre en el indice " + index + " es: " + nombre;
    }


    @PutMapping("/{index}/{nuevoNombre}")
    public String editar(@PathVariable("index") int index, @PathVariable("nuevoNombre") String nuevoNombre){

        String returnValidateIndex = validateIndex(index);
        if(!returnValidateIndex.isEmpty()) {
            return returnValidateIndex;
        }

        String returnValidateNombre = validateNombre(nuevoNombre);
        if(!returnValidateNombre.isEmpty()) {
            return returnValidateNombre;
        }

        int indexBuscar = index - 1;
        this.nombres.set(indexBuscar, nuevoNombre);

        return "El registro numero " + index + " fue actualizdo con el valor de " + nuevoNombre;
    }


    @DeleteMapping("/{index}")
    public String eliminar(@PathVariable("index") int index){

        String returnValidateIndex = validateIndex(index);
        if(!returnValidateIndex.isEmpty()) {
            return returnValidateIndex;
        }

        int indexEliminar = index - 1;
        this.nombres.remove(indexEliminar);

        return "El registro el index " + index + " fue eliminado con exito!";
    }

}
