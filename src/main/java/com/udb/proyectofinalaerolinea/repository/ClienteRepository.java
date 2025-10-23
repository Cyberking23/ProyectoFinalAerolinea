package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailIgnoreCase(String email);
    Optional<Cliente> findByEmailIgnoreCase(String email);
}
