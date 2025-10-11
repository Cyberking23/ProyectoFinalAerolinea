package com.udb.proyectofinalaerolinea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    @GetMapping("/VueloCliente")
    public String vueloCliente(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        // 👇 OJO: debe coincidir con la carpeta y nombre exactos
        return "Cliente/VueloCliente";
    }
    @GetMapping("/VerListadoVuelos")
    public String VerListadoVuelos(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        // 👇 OJO: debe coincidir con la carpeta y nombre exactos
        return "Cliente/VerListadoVuelos";
    }
}