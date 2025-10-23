package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Usuario;
import com.udb.proyectofinalaerolinea.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepo;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        email = email == null ? "" : email.trim().toLowerCase();

        Usuario u = usuarioRepo.findActivoByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado o inactivo"));

        String rol = (u.getRol() != null) ? u.getRol().name() : "USER";
        var auths = List.of(new SimpleGrantedAuthority("ROLE_" + rol));
        boolean enabled = "Activo".equalsIgnoreCase(u.getEstado());

        return User.withUsername(email)
                .password(u.getPassword())   // BCrypt en BD
                .authorities(auths)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!enabled)
                .build();
    }
}
