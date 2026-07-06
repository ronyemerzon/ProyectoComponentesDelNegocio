package com.ico.proyectojavaidat.repository;

import com.ico.proyectojavaidat.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepository {
    private final Map<Long, Cliente> clientes = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public ClienteRepository() {
        // Datos iniciales de prueba (Semilla)
        save(new Cliente(null, "Juan Pérez", "juan.perez@example.com", "987654321", "Tech Solutions"));
        save(new Cliente(null, "Maria Gomez", "maria.gomez@example.com", "912345678", "Global Retail"));
        save(new Cliente(null, "Carlos Lopez", "carlos.lopez@example.com", "945612378", "Innova SAC"));
    }

    public List<Cliente> findAll() {
        return new ArrayList<>(clientes.values());
    }

    public Optional<Cliente> findById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(clientes.get(id));
    }

    public Cliente save(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(idGenerator.incrementAndGet());
        }
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    public boolean deleteById(Long id) {
        if (id == null) return false;
        return clientes.remove(id) != null;
    }

    public boolean existsById(Long id) {
        if (id == null) return false;
        return clientes.containsKey(id);
    }
}
