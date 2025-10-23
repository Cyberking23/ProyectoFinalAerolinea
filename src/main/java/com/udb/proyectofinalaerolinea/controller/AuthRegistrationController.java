// controller/AuthRegistrationController.java
package com.udb.proyectofinalaerolinea.controller;

import com.udb.proyectofinalaerolinea.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/registro")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String nombre = body.getOrDefault("nombre", "").trim();
        String correo = body.getOrDefault("correo", "").trim();
        String pass   = body.getOrDefault("contrasena", "");
        String rep    = body.getOrDefault("repetir", "");

        if (nombre.isBlank() || correo.isBlank() || pass.isBlank() || rep.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Todos los campos son obligatorios"));
        }
        if (!pass.equals(rep)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Las contraseñas no coinciden"));
        }

        try {
            registrationService.registerCliente(nombre, correo, pass);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Registro exitoso. Ahora puedes iniciar sesión."));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", ex.getMessage())); // correo duplicado
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al registrar"));
        }
    }
}
