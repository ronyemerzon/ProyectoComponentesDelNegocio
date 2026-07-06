package com.ico.proyectojavaidat.controller;

import com.ico.proyectojavaidat.model.Tecnico;
import com.ico.proyectojavaidat.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
@Tag(name = "Técnicos", description = "Endpoints para la gestión de personal técnico")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los técnicos", description = "Retorna una lista con todos los técnicos registrados en el sistema.")
    public ResponseEntity<List<Tecnico>> listarTodos() {
        return ResponseEntity.ok(tecnicoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar técnico por ID", description = "Retorna un técnico específico según su ID. Lanza excepción 404 si no existe.")
    public ResponseEntity<Tecnico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo técnico", description = "Crea un nuevo técnico de soporte. Valida los campos y el formato de email.")
    public ResponseEntity<Tecnico> guardar(@Valid @RequestBody Tecnico tecnico) {
        Tecnico nuevoTecnico = tecnicoService.guardar(tecnico);
        return new ResponseEntity<>(nuevoTecnico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar técnico existente", description = "Actualiza los datos del técnico (nombre, especialidad, email, estado).")
    public ResponseEntity<Tecnico> actualizar(@PathVariable Long id, @Valid @RequestBody Tecnico tecnico) {
        return ResponseEntity.ok(tecnicoService.actualizar(id, tecnico));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar técnico", description = "Elimina físicamente a un técnico de la lista en memoria según su ID.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tecnicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
