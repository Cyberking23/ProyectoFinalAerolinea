package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Administrador;
import com.udb.proyectofinalaerolinea.model.Rol;
import com.udb.proyectofinalaerolinea.model.Usuario;
import com.udb.proyectofinalaerolinea.repository.AdministradorRepository;
import com.udb.proyectofinalaerolinea.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// service/impl/AdministradorServiceImpl.java
@Service
@Transactional
public class AdministradorServiceImpl implements AdministradorService {
    private final AdministradorRepository adminRepo;
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder encoder;

    public AdministradorServiceImpl(AdministradorRepository a, UsuarioRepository u, PasswordEncoder e){
        this.adminRepo=a; this.usuarioRepo=u; this.encoder=e;
    }

    @Override
    public Administrador crearAdmin(String nombre, String email, String rawPassword) {
        if (adminRepo.existsByEmail(email)) throw new IllegalArgumentException("Email ya usado (admin).");
        Usuario u = new Usuario();
        u.setPassword(encoder.encode(rawPassword));
        u.setRol(Rol.ADMIN);
        u.setEstado("Activo");
        usuarioRepo.save(u);

        Administrador a = new Administrador();
        a.setNombre(nombre);
        a.setEmail(email);
        a.setUsuario(u);
        return adminRepo.save(a);
    }

    @Override
    public Administrador actualizarAdmin(Long adminId, String nombre, String email, String nuevoPasswordNullable) {
        Administrador a = adminRepo.findById(adminId).orElseThrow(() -> new IllegalArgumentException("No existe administrador"));
        if (!a.getEmail().equals(email) && adminRepo.existsByEmail(email))
            throw new IllegalArgumentException("Email ya usado.");
        a.setNombre(nombre);
        a.setEmail(email);
        if (nuevoPasswordNullable != null && !nuevoPasswordNullable.isBlank()) {
            Usuario u = a.getUsuario();
            u.setPassword(encoder.encode(nuevoPasswordNullable));
            usuarioRepo.save(u);
        }
        return adminRepo.save(a);
    }

    @Override
    public void eliminarAdmin(Long adminId) {
        Administrador a = adminRepo.findById(adminId).orElseThrow(() -> new IllegalArgumentException("No existe administrador"));
        usuarioRepo.deleteById(a.getUsuario().getId()); // ON DELETE CASCADE borra admin
    }
}
