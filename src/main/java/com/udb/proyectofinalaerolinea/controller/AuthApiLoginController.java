package com.udb.proyectofinalaerolinea.controller;

import com.udb.proyectofinalaerolinea.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthApiLoginController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthApiLoginController(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.getOrDefault("username", body.getOrDefault("email", "")).trim().toLowerCase();
        String password = body.getOrDefault("password", "");

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // 🔧 Tomamos el UserDetails autenticado y generamos el token con él
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(principal);

        String rol = auth.getAuthorities().stream()
                .findFirst().map(GrantedAuthority::getAuthority).orElse("")
                .replace("ROLE_", "");

        return Map.of("token", token, "rol", rol);
    }
}
