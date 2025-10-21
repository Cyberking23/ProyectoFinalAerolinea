package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Aerolinea;
import com.udb.proyectofinalaerolinea.model.Tripulante;
import com.udb.proyectofinalaerolinea.repository.AerolineaRepository;
import com.udb.proyectofinalaerolinea.repository.TripulanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripulanteServiceImpl implements TripulanteService {

    private final TripulanteRepository tripRepo;
    private final AerolineaRepository aeroRepo;

    public TripulanteServiceImpl(TripulanteRepository tripRepo, AerolineaRepository aeroRepo) {
        this.tripRepo = tripRepo;
        this.aeroRepo = aeroRepo;
    }

    @Override
    public List<Tripulante> listarTodos() {
        return tripRepo.findAll();
    }

    @Override
    public Tripulante obtenerPorId(Long id) {
        return tripRepo.findById(id).orElse(null);
    }

    @Override
    public Tripulante guardar(Tripulante t, Long aerolineaId) {
        Aerolinea a = aeroRepo.findById(aerolineaId).orElse(null);
        t.setAerolinea(a);
        return tripRepo.save(t);
    }

    @Override
    public Tripulante actualizar(Long id, Tripulante t, Long aerolineaId) {
        return tripRepo.findById(id).map(ex -> {
            ex.setNombre(t.getNombre());
            ex.setRol(t.getRol());
            ex.setDisponibilidad(t.getDisponibilidad());
            if (aerolineaId != null) {
                Aerolinea a = aeroRepo.findById(aerolineaId).orElse(null);
                ex.setAerolinea(a);
            }
            return tripRepo.save(ex);
        }).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        tripRepo.deleteById(id);
    }
}
