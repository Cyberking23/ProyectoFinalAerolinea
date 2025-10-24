// PasajerosServiceImpl.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Pasajeros;
import com.udb.proyectofinalaerolinea.repository.PasajerosRepository;
import com.udb.proyectofinalaerolinea.service.PasajerosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajerosServiceImpl implements PasajerosService {

    @Autowired
    private PasajerosRepository pasajerosRepository;

    @Override
    public List<Pasajeros> findAll() {
        return pasajerosRepository.findAll();
    }

    @Override
    public Optional<Pasajeros> findById(Long id) {
        return pasajerosRepository.findById(id);
    }

    @Override
    public Pasajeros save(Pasajeros pasajero) {
        return pasajerosRepository.save(pasajero);
    }

    @Override
    public Pasajeros update(Long id, Pasajeros pasajero) {
        if (pasajerosRepository.existsById(id)) {
            pasajero.setId(id);
            return pasajerosRepository.save(pasajero);
        }
        throw new RuntimeException("Pasajero no encontrado con id: " + id);
    }

    @Override
    public void deleteById(Long id) {
        pasajerosRepository.deleteById(id);
    }

    @Override
    public List<Pasajeros> findByRegistradoPor(Long usuarioId) {
        return pasajerosRepository.findByRegistradoPorId(usuarioId);
    }
}