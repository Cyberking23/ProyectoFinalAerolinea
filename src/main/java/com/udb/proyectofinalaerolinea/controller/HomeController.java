package com.udb.proyectofinalaerolinea.controller;

import com.udb.proyectofinalaerolinea.model.Rol;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectFromRoot(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificar si el usuario está autenticado (no es anonymousUser)
        if (authentication != null &&
                authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {

            // Redirigir según el rol del usuario
            return redirectByRole(authentication);
        }

        // Si no está autenticado, ir al login
        return "redirect:/auth";
    }

    private String redirectByRole(Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        boolean isCliente = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("ROLE_CLIENTE"));

        if (isAdmin) {
            return "redirect:/admin/dashboard";
        } else if (isCliente) {
            return "redirect:/cliente/dashboard";
        }

        // Rol no reconocido, ir al login
        return "redirect:/auth";
    }
}