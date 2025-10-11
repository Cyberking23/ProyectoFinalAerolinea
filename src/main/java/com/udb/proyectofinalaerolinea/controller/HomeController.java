package com.udb.proyectofinalaerolinea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // cuando el usuario entra a la raíz (http://localhost:8080/)
    public String home(Model model) {
        model.addAttribute("titulo", "Página principal - Aerolínea");
        return "index"; // busca templates/index.html
    }
}