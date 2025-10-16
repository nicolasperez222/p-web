package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

      @GetMapping("/clientes")
    public String clientes() {
        return "clientes"; // busca clientes.html en /templates
    }

    @GetMapping("/resoluciones")
    public String resoluciones() {
        return "resoluciones"; 
    }

    @GetMapping("/software")
    public String software() {
        return "software"; 
    }

    @GetMapping("/certificados")
    public String certificados() {
        return "certificados"; 
    }
}