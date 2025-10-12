package com.udb.proyectofinalaerolinea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministradorController {
    @GetMapping("/RegistroAerolinea")
    public String RegistroAerolinea(Model model) {
        model.addAttribute("titulo", "Registro Aerolinea");
        return "Administrador/RegistroAerolinea";
    }
    @GetMapping("/RegistroTripulacion")
    public String RegistroTripulacion(Model model) {
        model.addAttribute("titulo", "Registro Aerolinea");
        return "Administrador/RegistroTripulacion";
    }
    @GetMapping("/RegistroAviones")
    public String RegistroAviones(Model model) {
        model.addAttribute("titulo", "Registro Aerolinea");
        return "Administrador/RegistroAviones";
    }
    @GetMapping("/RegistroVuelo")
    public String RegistroVuelos(Model model) {
        model.addAttribute("titulo", "Registro Aerolinea");
        return "Administrador/RegistroVuelos";
    }
    @GetMapping("/Quejas")
    public String Quejas(Model model) {
        model.addAttribute("titulo", "Registro Aerolinea");
        return "Administrador/Quejas";
    }
    @GetMapping("/Estadisticas")
    public String Estadisticas(Model model) {
        model.addAttribute("titulo", "Registro Aerolinea");
        return "Administrador/Estadisticas";
    }
}
