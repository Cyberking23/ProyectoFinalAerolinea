// src/main/java/com/udb/proyectofinalaerolinea/service/VueloServiceImpl.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Aerolinea;
import com.udb.proyectofinalaerolinea.model.Avion;
import com.udb.proyectofinalaerolinea.model.Vuelo;
import com.udb.proyectofinalaerolinea.repository.AerolineaRepository;
import com.udb.proyectofinalaerolinea.repository.AvionRepository;
import com.udb.proyectofinalaerolinea.repository.VueloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VueloServiceImpl implements VueloService {

    private final VueloRepository vueloRepository;
    private final AvionRepository avionRepository;
    private final AerolineaRepository aerolineaRepository;

    public VueloServiceImpl(VueloRepository vueloRepository,
                            AvionRepository avionRepository,
                            AerolineaRepository aerolineaRepository) {
        this.vueloRepository = vueloRepository;
        this.avionRepository = avionRepository;
        this.aerolineaRepository = aerolineaRepository;
    }

    @Override
    public List<Vuelo> listarTodos() {
        return vueloRepository.findAll();
    }

    @Override
    public Vuelo obtenerPorId(Long id) {
        return vueloRepository.findById(id).orElse(null);
    }

    @Override
    public Vuelo guardar(Vuelo vuelo, Long avionId, Long aerolineaId) {
        Avion avion = avionRepository.findById(avionId).orElseThrow();
        Aerolinea aerolinea = aerolineaRepository.findById(aerolineaId).orElseThrow();
        vuelo.setAvion(avion);
        vuelo.setAerolinea(aerolinea);
        return vueloRepository.save(vuelo);
    }

    @Override
    public Vuelo actualizar(Long id, Vuelo vuelo, Long avionId, Long aerolineaId) {
        Vuelo db = vueloRepository.findById(id).orElseThrow();
        db.setIdentificador(vuelo.getIdentificador());
        db.setOrigen(vuelo.getOrigen());
        db.setDestino(vuelo.getDestino());
        db.setDistanciaKm(vuelo.getDistanciaKm());
        db.setDuracionHoras(vuelo.getDuracionHoras());
        db.setTarifa(vuelo.getTarifa());
        db.setPuerta(vuelo.getPuerta());
        db.setCheckIn(vuelo.getCheckIn());
        db.setFechaSalida(vuelo.getFechaSalida());
        db.setFechaLlegada(vuelo.getFechaLlegada());
        if (avionId != null) db.setAvion(avionRepository.findById(avionId).orElseThrow());
        if (aerolineaId != null) db.setAerolinea(aerolineaRepository.findById(aerolineaId).orElseThrow());
        return vueloRepository.save(db);
    }

    @Override
    public void eliminar(Long id) {
        vueloRepository.deleteById(id);
    }
}
