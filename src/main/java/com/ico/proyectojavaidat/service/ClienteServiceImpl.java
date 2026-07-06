package com.ico.proyectojavaidat.service;

import com.ico.proyectojavaidat.exception.ResourceNotFoundException;
import com.ico.proyectojavaidat.model.Cliente;
import com.ico.proyectojavaidat.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = buscarPorId(id);
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setEmail(clienteActualizado.getEmail());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());
        clienteExistente.setEmpresa(clienteActualizado.getEmpresa());
        return clienteRepository.save(clienteExistente);
    }

    @Override
    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
