package com.ico.proyectojavaidat.service;

import com.ico.proyectojavaidat.dto.SolicitudRequest;
import com.ico.proyectojavaidat.exception.ResourceNotFoundException;
import com.ico.proyectojavaidat.model.Cliente;
import com.ico.proyectojavaidat.model.EstadoSolicitud;
import com.ico.proyectojavaidat.model.Solicitud;
import com.ico.proyectojavaidat.model.Tecnico;
import com.ico.proyectojavaidat.repository.ClienteRepository;
import com.ico.proyectojavaidat.repository.SolicitudRepository;
import com.ico.proyectojavaidat.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository,
                                ClienteRepository clienteRepository,
                                TecnicoRepository tecnicoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public List<Solicitud> listarTodas() {
        return solicitudRepository.findAll();
    }

    @Override
    public Solicitud buscarPorId(Long id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de soporte no encontrada con ID: " + id));
    }

    @Override
    public Solicitud guardar(SolicitudRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + request.getClienteId()));

        Tecnico tecnico = null;
        if (request.getTecnicoId() != null) {
            tecnico = tecnicoRepository.findById(request.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + request.getTecnicoId()));
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setDescripcion(request.getDescripcion());
        solicitud.setPrioridad(request.getPrioridad());
        solicitud.setCliente(cliente);
        solicitud.setTecnico(tecnico);
        
        solicitud.setEstado(request.getEstado() != null ? request.getEstado() : EstadoSolicitud.PENDIENTE);

        return solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud actualizar(Long id, SolicitudRequest request) {
        Solicitud solicitudExistente = buscarPorId(id);

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + request.getClienteId()));

        Tecnico tecnico = null;
        if (request.getTecnicoId() != null) {
            tecnico = tecnicoRepository.findById(request.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + request.getTecnicoId()));
        }

        solicitudExistente.setDescripcion(request.getDescripcion());
        solicitudExistente.setPrioridad(request.getPrioridad());
        solicitudExistente.setCliente(cliente);
        solicitudExistente.setTecnico(tecnico);

        if (request.getEstado() != null) {
            solicitudExistente.setEstado(request.getEstado());
        }

        return solicitudRepository.save(solicitudExistente);
    }

    @Override
    public void eliminar(Long id) {
        if (!solicitudRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Solicitud no encontrada con ID: " + id);
        }
        solicitudRepository.deleteById(id);
    }
}
