package com.udb.proyectofinalaerolinea.controller;

import com.udb.proyectofinalaerolinea.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String username = body.getOrDefault("username",
                    body.getOrDefault("email", "")).trim().toLowerCase();
            String password = body.getOrDefault("password", "");

            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails principal = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(principal);

            String rol = auth.getAuthorities().stream()
                    .findFirst().map(GrantedAuthority::getAuthority).orElse("")
                    .replace("ROLE_", "");

            return ResponseEntity.ok(Map.of("token", token, "rol", rol));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        }
    }
}
