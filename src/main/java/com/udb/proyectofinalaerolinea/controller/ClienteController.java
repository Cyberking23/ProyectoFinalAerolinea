package com.udb.proyectofinalaerolinea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    @GetMapping("/VueloCliente")
    public String vueloCliente(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
        return "Cliente/VueloCliente";
    }
    @GetMapping("/VerListadoVuelos")
    public String VerListadoVuelos(Model model) {
        model.addAttribute("titulo", "Vuelos del Cliente");
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