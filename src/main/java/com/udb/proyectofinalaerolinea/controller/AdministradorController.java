package com.udb.proyectofinalaerolinea.controller;

import com.udb.proyectofinalaerolinea.model.*;
import com.udb.proyectofinalaerolinea.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdministradorController {

    // ====== SERVICES ======
    private final AerolineaService aerolineaService;
    private final TripulanteService tripulanteService;
    private final AvionService avionService;
    private final VueloService vueloService;
    private final QuejaService quejaService;
    private final EstadisticasService estadisticasService; // <-- NUEVO

    public AdministradorController(AerolineaService aerolineaService,
                                   TripulanteService tripulanteService,
                                   AvionService avionService,
                                   VueloService vueloService,
                                   QuejaService quejaService,
                                   EstadisticasService estadisticasService) { // <-- NUEVO
        this.aerolineaService = aerolineaService;
        this.tripulanteService = tripulanteService;
        this.avionService = avionService;
        this.vueloService = vueloService;
        this.quejaService = quejaService;
        this.estadisticasService = estadisticasService;     // <-- NUEVO
    }

    // =========================================================================
    // PÁGINAS
    // =========================================================================
    @GetMapping("/admin/RegistroAerolinea")
    public String registroAerolinea(Model model) {
        model.addAttribute("titulo", "Registro Aerolínea");
        model.addAttribute("aerolineas", aerolineaService.listarTodas());
        model.addAttribute("aerolineaForm", new Aerolinea());
        return "Administrador/RegistroAerolinea";
    }

    @GetMapping("/admin/RegistroTripulacion")
    public String registroTripulacion(Model model) {
        model.addAttribute("titulo", "Registro Tripulación");
        model.addAttribute("tripulantes", tripulanteService.listarTodos());
        model.addAttribute("aerolineas", aerolineaService.listarTodas());
        model.addAttribute("tripulanteForm", new Tripulante());
        return "Administrador/RegistroTripulacion";
    }

    @GetMapping("/admin/RegistroAviones")
    public String registroAviones(Model model) {
        model.addAttribute("titulo", "Gestión de Aviones");
        model.addAttribute("aviones", avionService.listarTodos());
        model.addAttribute("aerolineas", aerolineaService.listarTodas());
        model.addAttribute("avionForm", new Avion());
        return "Administrador/RegistroAviones";
    }

    @GetMapping("/admin/RegistroVuelo")
    public String registroVuelos(Model model) {
        model.addAttribute("titulo", "Registro de Vuelos");
        model.addAttribute("vuelos", vueloService.listarTodos());
        model.addAttribute("aviones", avionService.listarTodos());
        model.addAttribute("aerolineas", aerolineaService.listarTodas());
        model.addAttribute("vueloForm", new Vuelo()); // requerido por el form del modal
        return "Administrador/RegistroVuelos";
    }

    @GetMapping("/admin/Quejas")
    public String quejas(Model model) {
        model.addAttribute("titulo", "Informe de Quejas");
        model.addAttribute("quejas", quejaService.listarTodas());
        return "Administrador/Quejas";
    }

    @GetMapping("/admin/Estadisticas")
    public String estadisticas(Model model) {
        var resumen = estadisticasService.resumenTotales();     // <-- NUEVO
        model.addAttribute("titulo", "Estadísticas");
        model.addAttribute("reservasCount", resumen.getOrDefault("reservas", 0L));        // tarjetas
        model.addAttribute("quejasCount", resumen.getOrDefault("quejas", 0L));
        model.addAttribute("canceladosCount", resumen.getOrDefault("vuelosCancelados", 0L));
        model.addAttribute("ultimasQuejas", estadisticasService.ultimasQuejas(10));       // tabla
        return "Administrador/Estadisticas";
    }

    // =========================================================================
    // CRUD AEROLÍNEA
    // =========================================================================
    @PostMapping("/admin/aerolineas")
    public String crearAerolinea(@Valid @ModelAttribute("aerolineaForm") Aerolinea aerolinea,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro Aerolínea");
            model.addAttribute("aerolineas", aerolineaService.listarTodas());
            return "Administrador/RegistroAerolinea";
        }
        aerolineaService.guardar(aerolinea);
        return "redirect:/RegistroAerolinea";
    }

    @GetMapping("/admin/aerolineas/{id}")
    @ResponseBody
    public Aerolinea obtenerAerolinea(@PathVariable Long id) {
        return aerolineaService.obtenerPorId(id);
    }

    @PostMapping("/admin/aerolineas/{id}/actualizar")
    public String actualizarAerolinea(@PathVariable Long id,
                                      @Valid @ModelAttribute("aerolineaForm") Aerolinea aerolinea,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro Aerolínea");
            model.addAttribute("aerolineas", aerolineaService.listarTodas());
            return "Administrador/RegistroAerolinea";
        }
        aerolineaService.actualizar(id, aerolinea);
        return "redirect:/RegistroAerolinea";
    }

    @PostMapping("/admin/aerolineas/{id}/eliminar")
    public String eliminarAerolinea(@PathVariable Long id) {
        aerolineaService.eliminar(id);
        return "redirect:/RegistroAerolinea";
    }

    // =========================================================================
    // CRUD TRIPULACIÓN
    // =========================================================================
    @PostMapping("/admin/tripulantes")
    public String crearTripulante(@Valid @ModelAttribute("tripulanteForm") Tripulante tripulante,
                                  BindingResult result,
                                  @RequestParam("aerolineaId") Long aerolineaId,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro Tripulación");
            model.addAttribute("tripulantes", tripulanteService.listarTodos());
            model.addAttribute("aerolineas", aerolineaService.listarTodas());
            return "Administrador/RegistroTripulacion";
        }
        tripulanteService.guardar(tripulante, aerolineaId);
        return "redirect:/RegistroTripulacion";
    }

    @GetMapping("/admin/tripulantes/{id}")
    @ResponseBody
    public Tripulante obtenerTripulante(@PathVariable Long id) {
        Tripulante t = tripulanteService.obtenerPorId(id);
        if (t != null && t.getAerolinea() != null) t.getAerolinea().getNombre();
        return t;
    }

    @PostMapping("/admin/tripulantes/{id}/actualizar")
    public String actualizarTripulante(@PathVariable Long id,
                                       @Valid @ModelAttribute("tripulanteForm") Tripulante tripulante,
                                       BindingResult result,
                                       @RequestParam("aerolineaId") Long aerolineaId,
                                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro Tripulación");
            model.addAttribute("tripulantes", tripulanteService.listarTodos());
            model.addAttribute("aerolineas", aerolineaService.listarTodas());
            return "Administrador/RegistroTripulacion";
        }
        tripulanteService.actualizar(id, tripulante, aerolineaId);
        return "redirect:/RegistroTripulacion";
    }

    @PostMapping("/admin/tripulantes/{id}/eliminar")
    public String eliminarTripulante(@PathVariable Long id) {
        tripulanteService.eliminar(id);
        return "redirect:/RegistroTripulacion";
    }

    // =========================================================================
    // CRUD AVIONES
    // =========================================================================
    @PostMapping("/admin/aviones")
    public String crearAvion(@Valid @ModelAttribute("avionForm") Avion avion,
                             BindingResult result,
                             @RequestParam("aerolineaId") Long aerolineaId,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Gestión de Aviones");
            model.addAttribute("aviones", avionService.listarTodos());
            model.addAttribute("aerolineas", aerolineaService.listarTodas());
            return "Administrador/RegistroAviones";
        }
        avionService.guardar(avion, aerolineaId);
        return "redirect:/RegistroAviones";
    }

    @GetMapping("/admin/aviones/{id}")
    @ResponseBody
    public Avion obtenerAvion(@PathVariable Long id) {
        Avion a = avionService.obtenerPorId(id);
        if (a != null && a.getAerolinea() != null) a.getAerolinea().getNombre();
        return a;
    }

    @PostMapping("/admin/aviones/{id}/actualizar")
    public String actualizarAvion(@PathVariable Long id,
                                  @Valid @ModelAttribute("avionForm") Avion avion,
                                  BindingResult result,
                                  @RequestParam("aerolineaId") Long aerolineaId,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Gestión de Aviones");
            model.addAttribute("aviones", avionService.listarTodos());
            model.addAttribute("aerolineas", aerolineaService.listarTodas());
            return "Administrador/RegistroAviones";
        }
        avionService.actualizar(id, avion, aerolineaId);
        return "redirect:/RegistroAviones";
    }

    @PostMapping("/admin/aviones/{id}/eliminar")
    public String eliminarAvion(@PathVariable Long id) {
        avionService.eliminar(id);
        return "redirect:/RegistroAviones";
    }

    // =========================================================================
    // CRUD VUELOS
    // =========================================================================
    @PostMapping("/admin/vuelos")
    public String crearVuelo(@Valid @ModelAttribute("vueloForm") Vuelo vuelo,
                             BindingResult result,
                             @RequestParam("avionId") Long avionId,
                             @RequestParam("aerolineaId") Long aerolineaId,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Vuelos");
            model.addAttribute("vuelos", vueloService.listarTodos());
            model.addAttribute("aviones", avionService.listarTodos());
            model.addAttribute("aerolineas", aerolineaService.listarTodas());
            return "Administrador/RegistroVuelos";
        }
        vueloService.guardar(vuelo, avionId, aerolineaId);
        return "redirect:/RegistroVuelo";
    }

    @GetMapping("/admin/vuelos/{id}")
    @ResponseBody
    public Vuelo obtenerVuelo(@PathVariable Long id) {
        Vuelo v = vueloService.obtenerPorId(id);
        if (v != null && v.getAerolinea() != null) v.getAerolinea().getNombre();
        if (v != null && v.getAvion() != null) v.getAvion().getModelo();
        return v;
    }

    @PostMapping("/admin/vuelos/{id}/actualizar")
    public String actualizarVuelo(@PathVariable Long id,
                                  @Valid @ModelAttribute("vueloForm") Vuelo vuelo,
                                  BindingResult result,
                                  @RequestParam(value = "avionId", required = false) Long avionId,
                                  @RequestParam(value = "aerolineaId", required = false) Long aerolineaId,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Vuelos");
            model.addAttribute("vuelos", vueloService.listarTodos());
            model.addAttribute("aviones", avionService.listarTodos());
            model.addAttribute("aerolineas", aerolineaService.listarTodas());
            return "Administrador/RegistroVuelos";
        }
        vueloService.actualizar(id, vuelo, avionId, aerolineaId);
        return "redirect:/RegistroVuelo";
    }

    @PostMapping("/admin/vuelos/{id}/eliminar")
    public String eliminarVuelo(@PathVariable Long id) {
        vueloService.eliminar(id);
        return "redirect:/RegistroVuelo";
    }

    // =========================================================================
    // QUEJAS (ADMIN)
    // =========================================================================
    @GetMapping("/admin/quejas/{id}")
    @ResponseBody
    public Queja obtenerQueja(@PathVariable Long id) {
        Queja q = quejaService.obtenerPorId(id);
        if (q != null && q.getVuelo() != null) {
            // Forzar inicialización perezosa para poder serializar el vuelo
            q.getVuelo().getIdentificador();
        }
        return q;
    }

    @PostMapping("/admin/quejas/{id}/estado")
    public String cambiarEstadoQueja(@PathVariable Long id,
                                     @RequestParam("estado") String estado) {
        quejaService.actualizarEstado(id, EstadoQueja.valueOf(estado));
        return "redirect:/Quejas";
    }

    @PostMapping("/admin/quejas/{id}/eliminar")
    public String eliminarQueja(@PathVariable Long id) {
        quejaService.eliminar(id);
        return "redirect:/Quejas";
    }
}
