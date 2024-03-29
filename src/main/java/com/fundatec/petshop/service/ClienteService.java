package com.fundatec.petshop.service;

import ch.qos.logback.core.net.server.Client;
import com.fundatec.petshop.controller.request.ClienteRequest;
import com.fundatec.petshop.model.Cliente;
import com.fundatec.petshop.repository.ClienteRepository;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;

    }
    public Cliente criarNovo(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);

    }
    public void editarCliente(Long id, ClienteRequest clienteRequest) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " não existe"));
        clienteExistente.setNome(clienteRequest.getNome());
        clienteExistente.setCpf(clienteRequest.getCpf());

        clienteRepository.save(clienteExistente);
    }
}

