// src/main/java/com/udb/proyectofinalaerolinea/service/RegistrationServiceImpl.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Cliente;
import com.udb.proyectofinalaerolinea.model.Rol;
import com.udb.proyectofinalaerolinea.model.Usuario;
import com.udb.proyectofinalaerolinea.repository.AdministradorRepository;
import com.udb.proyectofinalaerolinea.repository.ClienteRepository;
import com.udb.proyectofinalaerolinea.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Usuario registerCliente(String nombre, String correo, String rawPassword) {
        final String email = correo.trim().toLowerCase();

        if (clienteRepository.existsByEmailIgnoreCase(email)
                || administradorRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        Usuario u = new Usuario();
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setRol(Rol.CLIENTE);
        u.setEstado("Activo");
        Usuario savedUser = usuarioRepository.save(u);

        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setEmail(email);
        c.setUsuario(savedUser);
        clienteRepository.save(c);

        return savedUser;
    }
}
