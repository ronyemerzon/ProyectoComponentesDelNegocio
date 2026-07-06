package com.ico.proyectojavaidat.service;

import com.ico.proyectojavaidat.model.Tecnico;

import java.util.List;

public interface TecnicoService {
    List<Tecnico> listarTodos();
    Tecnico buscarPorId(Long id);
    Tecnico guardar(Tecnico tecnico);
    Tecnico actualizar(Long id, Tecnico tecnico);
    void eliminar(Long id);
}
