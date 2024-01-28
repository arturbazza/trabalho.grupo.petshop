package com.fundatec.petshop.controller;

import com.fundatec.petshop.controller.request.ClienteRequest;
import com.fundatec.petshop.controller.response.ClienteResponse;
import com.fundatec.petshop.model.Cliente;
import com.fundatec.petshop.repository.ClienteRepository;
import com.fundatec.petshop.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path ="/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listaClientes(@RequestParam(name = "nome",required = false) String nome) {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponse> responseList = clientes.stream()
                .map(ClienteResponse::of)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    public ResponseEntity<Void> criarCliente(@RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toModel();
        clienteService.criarNovo(cliente);
        return ResponseEntity.created(URI.create("/clientes/" + cliente.getId())).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> editarCliente(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        if (clienteRepository.existsById(id)) {
            Cliente cliente = clienteRequest.toModel();
            cliente.setId(id);
            clienteRepository.save(cliente);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
