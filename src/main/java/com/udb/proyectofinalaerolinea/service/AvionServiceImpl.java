// src/main/java/com/udb/proyectofinalaerolinea/service/AvionServiceImpl.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Aerolinea;
import com.udb.proyectofinalaerolinea.model.Avion;
import com.udb.proyectofinalaerolinea.repository.AerolineaRepository;
import com.udb.proyectofinalaerolinea.repository.AvionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvionServiceImpl implements AvionService {

    private final AvionRepository avionRepo;
    private final AerolineaRepository aerolineaRepo;

    public AvionServiceImpl(AvionRepository avionRepo, AerolineaRepository aerolineaRepo) {
        this.avionRepo = avionRepo;
        this.aerolineaRepo = aerolineaRepo;
    }

    @Override
    public List<Avion> listarTodos() {
        return avionRepo.findAll();
    }

    @Override
    public Avion obtenerPorId(Long id) {
        return avionRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Avion guardar(Avion avion, Long aerolineaId) {
        Aerolinea a = aerolineaRepo.findById(aerolineaId).orElseThrow();
        avion.setAerolinea(a);
        return avionRepo.save(avion);
    }

    @Override
    @Transactional
    public Avion actualizar(Long id, Avion avion, Long aerolineaId) {
        Avion db = avionRepo.findById(id).orElseThrow();
        db.setMatricula(avion.getMatricula());
        db.setModelo(avion.getModelo());
        db.setCapacidad(avion.getCapacidad());
        db.setEstado(avion.getEstado());
        Aerolinea a = aerolineaRepo.findById(aerolineaId).orElseThrow();
        db.setAerolinea(a);
        return avionRepo.save(db);
    }

    @Override
    public void eliminar(Long id) {
        avionRepo.deleteById(id);
    }

    @Override
    public boolean existeMatricula(String matricula) {
        return avionRepo.existsByMatricula(matricula);
    }
}
