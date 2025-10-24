package com.udb.proyectofinalaerolinea.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AuthResponse", description = "Respuesta de autenticación con token JWT")
public class AuthResponse {

    @Schema(description = "JWT Bearer", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public AuthResponse(String token) { this.token = token; }
    public String getToken() { return token; }
}
