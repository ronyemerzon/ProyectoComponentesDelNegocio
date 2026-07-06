package com.ico.proyectojavaidat.repository;

import com.ico.proyectojavaidat.model.Tecnico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TecnicoRepository {
    private final Map<Long, Tecnico> tecnicos = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public TecnicoRepository() {
        // Datos iniciales de prueba (Semilla)
        save(new Tecnico(null, "Ing. Alejandro Rios", "Redes y Conectividad", "alejandro.rios@tech.com", "ACTIVO"));
        save(new Tecnico(null, "Sgte. Beatriz Luna", "Sistemas Operativos y Software", "beatriz.luna@tech.com", "ACTIVO"));
        save(new Tecnico(null, "Tec. Christian Dávila", "Hardware de Servidores", "christian.davila@tech.com", "INACTIVO"));
    }

    public List<Tecnico> findAll() {
        return new ArrayList<>(tecnicos.values());
    }

    public Optional<Tecnico> findById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(tecnicos.get(id));
    }

    public Tecnico save(Tecnico tecnico) {
        if (tecnico.getId() == null) {
            tecnico.setId(idGenerator.incrementAndGet());
        }
        tecnicos.put(tecnico.getId(), tecnico);
        return tecnico;
    }

    public boolean deleteById(Long id) {
        if (id == null) return false;
        return tecnicos.remove(id) != null;
    }

    public boolean existsById(Long id) {
        if (id == null) return false;
        return tecnicos.containsKey(id);
    }
}
