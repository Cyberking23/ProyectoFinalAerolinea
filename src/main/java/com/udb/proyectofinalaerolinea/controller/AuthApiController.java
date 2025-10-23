package com.udb.proyectofinalaerolinea.controller;

import com.udb.proyectofinalaerolinea.service.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthApiController {

    private final JwtService jwtService;

    public AuthApiController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /** Requiere estar autenticado por JWT; devuelve {token, rol}. */
    @GetMapping("/token")
    public Map<String, Object> token(Authentication auth) {
        String email = auth.getName(); // viene del SecurityContext por el filtro
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(principal);
        String rol = auth.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse("");
        rol = rol.replace("ROLE_","");
        return Map.of("token", token, "rol", rol);
    }
}
