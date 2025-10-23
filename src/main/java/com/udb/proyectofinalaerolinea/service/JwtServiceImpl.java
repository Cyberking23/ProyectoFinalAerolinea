// src/main/java/com/udb/proyectofinalaerolinea/service/JwtServiceImpl.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Usuario;
import com.udb.proyectofinalaerolinea.repository.UsuarioRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private final UsuarioRepository usuarioRepo;
    private final Key signingKey;
    private final long expirationMs;

    public JwtServiceImpl(
            UsuarioRepository usuarioRepo,
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-ms}") long expirationMs
    ) {
        this.usuarioRepo = usuarioRepo;
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        String email = userDetails.getUsername();
        String rol = userDetails.getAuthorities().stream()
                .findFirst().map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("CLIENTE");

        Long uid = usuarioRepo.findActivoByEmail(email).map(Usuario::getId).orElse(null);

        Map<String,Object> claims = new HashMap<>();
        claims.put("rol", rol);
        if (uid != null) claims.put("uid", uid);

        return Jwts.builder()
                .setSubject(email)
                .addClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && parseClaims(token).getBody().getExpiration().after(new Date());
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
    }
}
