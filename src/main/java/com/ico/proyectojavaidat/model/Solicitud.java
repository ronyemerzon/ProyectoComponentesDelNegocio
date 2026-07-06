package com.ico.proyectojavaidat.model;

import java.time.LocalDateTime;

public class Solicitud {
    private Long id;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private EstadoSolicitud estado;
    private PrioridadSolicitud prioridad;
    private Cliente cliente;
    private Tecnico tecnico;

    public Solicitud() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Solicitud(Long id, String descripcion, EstadoSolicitud estado, PrioridadSolicitud prioridad, Cliente cliente, Tecnico tecnico) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = estado;
        this.prioridad = prioridad;
        this.cliente = cliente;
        this.tecnico = tecnico;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public PrioridadSolicitud getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadSolicitud prioridad) {
        this.prioridad = prioridad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
}
