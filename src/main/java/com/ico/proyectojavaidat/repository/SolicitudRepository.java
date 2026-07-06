package com.ico.proyectojavaidat.repository;

import com.ico.proyectojavaidat.model.Cliente;
import com.ico.proyectojavaidat.model.EstadoSolicitud;
import com.ico.proyectojavaidat.model.PrioridadSolicitud;
import com.ico.proyectojavaidat.model.Solicitud;
import com.ico.proyectojavaidat.model.Tecnico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SolicitudRepository {
    private final Map<Long, Solicitud> solicitudes = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public SolicitudRepository(ClienteRepository clienteRepository, TecnicoRepository tecnicoRepository) {
        // Datos iniciales de prueba
        Optional<Cliente> cliente1 = clienteRepository.findById(1L);
        Optional<Cliente> cliente2 = clienteRepository.findById(2L);
        Optional<Tecnico> tecnico1 = tecnicoRepository.findById(1L);

        if (cliente1.isPresent()) {
            save(new Solicitud(null, "Problema de conexión con la VPN de la empresa", 
                    EstadoSolicitud.PENDIENTE, PrioridadSolicitud.ALTA, cliente1.get(), null));
        }
        if (cliente2.isPresent() && tecnico1.isPresent()) {
            save(new Solicitud(null, "Instalación de suite de diseño gráfico y licencias", 
                    EstadoSolicitud.EN_PROCESO, PrioridadSolicitud.MEDIA, cliente2.get(), tecnico1.get()));
        }
    }

    public List<Solicitud> findAll() {
        return new ArrayList<>(solicitudes.values());
    }

    public Optional<Solicitud> findById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(solicitudes.get(id));
    }

    public Solicitud save(Solicitud solicitud) {
        if (solicitud.getId() == null) {
            solicitud.setId(idGenerator.incrementAndGet());
        }
        solicitudes.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    public boolean deleteById(Long id) {
        if (id == null) return false;
        return solicitudes.remove(id) != null;
    }

    public boolean existsById(Long id) {
        if (id == null) return false;
        return solicitudes.containsKey(id);
    }
}
