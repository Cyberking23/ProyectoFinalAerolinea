package com.udb.proyectofinalaerolinea.controller;

import com.udb.proyectofinalaerolinea.service.VueloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    private final VueloService vueloService;

    // Inyección por constructor
    public ClienteController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping("/VueloCliente") // tommy
    public String vueloCliente(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        return "Cliente/VueloCliente";
    }

    @GetMapping("/VerListadoVuelos") // tommy
    public String VerListadoVuelos(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        // Cargar lista real de vuelos (Paso 2 cubre la implementación del servicio)
        model.addAttribute("vuelos", vueloService.listarTodos());
        return "Cliente/VerListadoVuelos";
    }

    @GetMapping("/InsertarDatosComprador")
    public String InsertarDatosComprador(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        return "Cliente/InsertarDatosComprador";
    }

    @GetMapping("/ConfirmacionVuelo")
    public String VerificacionInformacionVuelo(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        return "Cliente/ConfirmacionVuelo";
    }

    @GetMapping("/MetodoPago")
    public String MetodoPago(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        return "Cliente/MetodoPago";
    }

    @GetMapping("/CancelacionVuelo")
    public String CancelacionVuelo(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        return "Cliente/CancelacionVuelo";
    }

    @GetMapping("/Reclamo")
    public String Reclamo(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        return "Cliente/Reclamo";
    }
}
