package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Queja;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuejaRepository extends JpaRepository<Queja, Long> {

    List<Queja> findAllByOrderByFechaReclamoDesc(Pageable pageable);

    List<Queja> findAllByOrderByFechaReclamoDesc();
}
