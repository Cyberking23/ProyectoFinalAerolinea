package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.*;
import com.udb.proyectofinalaerolinea.repository.*;
import com.udb.proyectofinalaerolinea.service.AuthService;
import com.udb.proyectofinalaerolinea.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;

@SuppressWarnings("ALL")
@Service @Transactional
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepo;
    private final ClienteRepository clienteRepo;
    private final AdministradorRepository adminRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthServiceImpl(UsuarioRepository u, ClienteRepository c, AdministradorRepository a,
                           PasswordEncoder e, JwtService j){
        this.usuarioRepo = u; this.clienteRepo = c; this.adminRepo = a; this.encoder = e; this.jwt = j;
    }

    @Override
    public void registrarCliente(String nombre, String email, String rawPassword) {
        if (usuarioRepo.existsEmailGlobal(email))
            throw new IllegalArgumentException("El correo ya está en uso.");
        Usuario u = new Usuario();
        u.setPassword(encoder.encode(rawPassword));
        u.setRol(Rol.CLIENTE);
        u.setEstado("Activo");
        usuarioRepo.save(u);

        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setEmail(email);
        c.setUsuario(u);
        clienteRepo.save(c);
    }

    @Override
    public String emitirTokenPorEmail(String email) {
        Usuario u = usuarioRepo.findActivoByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No existe usuario activo con ese email"));

        // Construir el UserDetails que tu JwtService espera
        var springUser = org.springframework.security.core.userdetails.User
                .withUsername(email)                       // IMPORTANTÍSIMO: el "username" es el email
                .password(u.getPassword())                 // no se usa para firmar, pero es requerido por el builder
                .authorities("ROLE_" + u.getRol().name())  // tu JwtService extrae el rol de aquí
                .build();

        return jwt.generateToken(springUser);          // <-- ahora coincide con tu interfaz (1 arg)
    }

    @Override
    public String emitirTokenAutenticado(String email) {
        return emitirTokenPorEmail(email);
    }
}
