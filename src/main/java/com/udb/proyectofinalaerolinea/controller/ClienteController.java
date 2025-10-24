package com.udb.proyectofinalaerolinea.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.udb.proyectofinalaerolinea.model.Pasajeros;
import com.udb.proyectofinalaerolinea.model.Vuelo;
import com.udb.proyectofinalaerolinea.service.PasajerosService;
import com.udb.proyectofinalaerolinea.service.VueloService;
import com.udb.proyectofinalaerolinea.service.AvionService;
import com.udb.proyectofinalaerolinea.model.Avion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClienteController {

    private final VueloService vueloService;
    private final AvionService avionService;
    private final PasajerosService pasajerosService;

    // Inyección por constructor
    public ClienteController(VueloService vueloService, PasajerosService pasajerosService, AvionService avionService) {
        this.vueloService = vueloService;
        this.avionService = avionService;
        this.pasajerosService = pasajerosService;
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
    public String InsertarDatosComprador(@RequestParam("id") Long id, Model model) {
        Vuelo vuelo = vueloService.obtenerPorId(id);
        if (vuelo == null) {
            return "error";
        }
        Avion avion = avionService.obtenerPorId(vuelo.getAvion().getId());

        model.addAttribute("titulo", "Vuelos del Cliente");
        model.addAttribute("vuelo", vuelo);
        model.addAttribute("avion", avion);

        return "Cliente/InsertarDatosComprador";
    }

    @PostMapping("/guardarPasajeros")
    public String guardarPasajeros(@RequestParam("vueloId") Long vueloId,
                                   @RequestParam("pasajerosData") String pasajerosJson) {
        try {
            // Convertir JSON a lista de pasajeros
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            List<Pasajeros> pasajeros = objectMapper.readValue(pasajerosJson,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Pasajeros.class));

            // Guardar cada pasajero
            for (Pasajeros pasajero : pasajeros) {
                // No establecemos registradoPor - se guardará como null o con valor por defecto
                pasajerosService.save(pasajero);
            }

            return "redirect:/InsertarDatosComprador?vueloId=" + vueloId;

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
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
