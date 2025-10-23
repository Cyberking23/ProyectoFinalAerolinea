package com.udb.proyectofinalaerolinea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/auth")
    public String login(Model model) {
        model.addAttribute("titulo", "Login");
        return "Auth/Login";
    }
    @GetMapping("/auth/registro")
    public String registro(Model model) {
        model.addAttribute("titulo", "Registro");
        return "Auth/Registro";
    }
}