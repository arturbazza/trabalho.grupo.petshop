package com.fundatec.petshop.service;

import com.fundatec.petshop.model.Cliente;
import com.fundatec.petshop.repository.ClienteRepository;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);

    }

    public void editCliente(Long id,
                            Cliente cliente) {
        clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " não existe"));

        cliente.setId(id);
        clienteRepository.save(cliente);

    }

}

