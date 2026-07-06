package com.ico.proyectojavaidat.service;

import com.ico.proyectojavaidat.exception.ResourceNotFoundException;
import com.ico.proyectojavaidat.model.Tecnico;
import com.ico.proyectojavaidat.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public List<Tecnico> listarTodos() {
        return tecnicoRepository.findAll();
    }

    @Override
    public Tecnico buscarPorId(Long id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + id));
    }

    @Override
    public Tecnico guardar(Tecnico tecnico) {
        tecnico.setId(null);
        return tecnicoRepository.save(tecnico);
    }

    @Override
    public Tecnico actualizar(Long id, Tecnico tecnicoActualizado) {
        Tecnico tecnicoExistente = buscarPorId(id);
        tecnicoExistente.setNombre(tecnicoActualizado.getNombre());
        tecnicoExistente.setEspecialidad(tecnicoActualizado.getEspecialidad());
        tecnicoExistente.setEmail(tecnicoActualizado.getEmail());
        tecnicoExistente.setEstado(tecnicoActualizado.getEstado());
        return tecnicoRepository.save(tecnicoExistente);
    }

    @Override
    public void eliminar(Long id) {
        if (!tecnicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Técnico no encontrado con ID: " + id);
        }
        tecnicoRepository.deleteById(id);
    }
}
