package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.EstadoQueja;
import com.udb.proyectofinalaerolinea.model.Queja;
import com.udb.proyectofinalaerolinea.model.Vuelo;
import com.udb.proyectofinalaerolinea.repository.QuejaRepository;
import com.udb.proyectofinalaerolinea.repository.VueloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class QuejaServiceImpl implements QuejaService {

    private final QuejaRepository quejaRepository;
    private final VueloRepository vueloRepository;

    public QuejaServiceImpl(QuejaRepository quejaRepository, VueloRepository vueloRepository) {
        this.quejaRepository = quejaRepository;
        this.vueloRepository = vueloRepository;
    }

    @Override
    public Queja crearDesdePublico(String identificadorVuelo, String asunto, String descripcion) {
        Vuelo vuelo = null;
        if (identificadorVuelo != null && !identificadorVuelo.isBlank()) {
            // ⇩⇩ CORRECCIÓN: usar el nombre del campo Java (identificador), no el de la columna SQL
            vuelo = vueloRepository.findByIdentificador(identificadorVuelo).orElse(null);
        }

        Queja q = new Queja();
        q.setAsunto(asunto);
        q.setDescripcion(descripcion);
        q.setFechaReclamo(LocalDate.now());
        q.setEstado(EstadoQueja.PENDIENTE);
        q.setVuelo(vuelo);
        return quejaRepository.save(q);
    }

    @Override
    public List<Queja> listarTodas() {
        return quejaRepository.findAllByOrderByFechaReclamoDesc();
    }

    @Override
    public Queja obtenerPorId(Long id) {
        return quejaRepository.findById(id).orElse(null);
    }

    @Override
    public void actualizarEstado(Long id, EstadoQueja nuevoEstado) {
        Queja q = quejaRepository.findById(id).orElseThrow();
        q.setEstado(nuevoEstado);
        quejaRepository.save(q);
    }

    @Override
    public void eliminar(Long id) {
        quejaRepository.deleteById(id);
    }
}
