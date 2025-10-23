package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByEmail(String email);
    boolean existsByEmail(String email);
}
