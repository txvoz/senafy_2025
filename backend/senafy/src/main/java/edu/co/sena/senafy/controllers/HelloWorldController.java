package edu.co.sena.senafy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping()
    public String inicio(){
        System.out.println("Paso por el inicio");
        return "Esta en el inicio";
    }

    @GetMapping("/saludar")
    public String saludar(){
        System.out.println("Paso por el metodo saludar");
        return "Hola Adso 2026   ";
    }

    @GetMapping("/despedir")
    public String despedir(){
        System.out.println("asdasd asdasd  asd");
        System.out.println("Paso por el metodo despedir ");
        return "Chao Adso 2026 ";
    }


}
