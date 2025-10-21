package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Aerolinea;
import com.udb.proyectofinalaerolinea.repository.AerolineaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AerolineaServiceImpl implements AerolineaService {

    private final AerolineaRepository repository;

    public AerolineaServiceImpl(AerolineaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Aerolinea> listarTodas() {
        return repository.findAll();
    }

    @Override
    public Aerolinea obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Aerolinea guardar(Aerolinea aerolinea) {
        return repository.save(aerolinea);
    }

    @Override
    public Aerolinea actualizar(Long id, Aerolinea aerolinea) {
        return repository.findById(id)
                .map(existente -> {
                    existente.setNombre(aerolinea.getNombre());
                    existente.setCodigoIataIcao(aerolinea.getCodigoIataIcao());
                    existente.setPaisOrigen(aerolinea.getPaisOrigen());
                    existente.setEstado(aerolinea.getEstado());
                    return repository.save(existente);
                })
                .orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
