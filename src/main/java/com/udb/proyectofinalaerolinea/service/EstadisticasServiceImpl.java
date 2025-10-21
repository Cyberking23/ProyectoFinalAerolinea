package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Queja;
import com.udb.proyectofinalaerolinea.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class EstadisticasServiceImpl implements EstadisticasService {

    private final VueloRepository vueloRepository;
    private final AvionRepository avionRepository;
    private final AerolineaRepository aerolineaRepository;
    private final QuejaRepository quejaRepository;
    private final ReservacionRepository reservacionRepository;

    public EstadisticasServiceImpl(
            VueloRepository vueloRepository,
            AvionRepository avionRepository,
            AerolineaRepository aerolineaRepository,
            QuejaRepository quejaRepository,
            ReservacionRepository reservacionRepository) {
        this.vueloRepository = vueloRepository;
        this.avionRepository = avionRepository;
        this.aerolineaRepository = aerolineaRepository;
        this.quejaRepository = quejaRepository;
        this.reservacionRepository = reservacionRepository;
    }

    @Override
    public Map<String, Long> resumenTotales() {
        Map<String, Long> m = new LinkedHashMap<>();
        m.put("reservas", reservacionRepository.count());
        m.put("quejas", quejaRepository.count());
        // Si quieres “vuelos cancelados” real, agrega campo/estado en Vuelo y cuenta.
        // Por ahora lo dejamos en 0 si no existe tal campo:
        m.put("vuelosCancelados", 0L);
        return m;
    }

    @Override
    public List<Queja> ultimasQuejas(int limite) {
        return quejaRepository.findAllByOrderByFechaReclamoDesc(PageRequest.of(0, limite));
    }
}
