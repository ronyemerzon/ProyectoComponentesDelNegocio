package com.ico.proyectojavaidat.controller;

import com.ico.proyectojavaidat.dto.SolicitudRequest;
import com.ico.proyectojavaidat.model.Solicitud;
import com.ico.proyectojavaidat.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Solicitudes", description = "Endpoints para el registro y ciclo de vida de solicitudes de soporte técnico")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las solicitudes", description = "Retorna una lista con todas las solicitudes de soporte registradas.")
    public ResponseEntity<List<Solicitud>> listarTodas() {
        return ResponseEntity.ok(solicitudService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar solicitud por ID", description = "Retorna una solicitud específica de soporte técnico según su ID.")
    public ResponseEntity<Solicitud> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(solicitudService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva solicitud de soporte", description = "Crea una solicitud de soporte en estado PENDIENTE. Requiere un clienteId válido y opcionalmente un tecnicoId.")
    public ResponseEntity<Solicitud> guardar(@Valid @RequestBody SolicitudRequest request) {
        Solicitud nuevaSolicitud = solicitudService.guardar(request);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar solicitud de soporte", description = "Permite actualizar la descripción, prioridad, estado y asignar/cambiar cliente y técnico.")
    public ResponseEntity<Solicitud> actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudRequest request) {
        return ResponseEntity.ok(solicitudService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud", description = "Elimina físicamente una solicitud del almacén en memoria según su ID.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        solicitudService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
