package com.ico.proyectojavaidat.service;

import com.ico.proyectojavaidat.dto.SolicitudRequest;
import com.ico.proyectojavaidat.model.Solicitud;

import java.util.List;

public interface SolicitudService {
    List<Solicitud> listarTodas();
    Solicitud buscarPorId(Long id);
    Solicitud guardar(SolicitudRequest request);
    Solicitud actualizar(Long id, SolicitudRequest request);
    void eliminar(Long id);
}
