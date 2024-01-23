package com.fundatec.petshop.controller;

import com.fundatec.petshop.controller.request.ClienteRequest;
import com.fundatec.petshop.controller.response.ClienteResponse;
import com.fundatec.petshop.model.Cliente;
import com.fundatec.petshop.repository.ClienteRepository;
import com.fundatec.petshop.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("listar")
    public List<ClienteResponse> listaClientes(@RequestParam(required = false) String nome) {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(ClienteResponse::of)
                .toList();
    }

    @PostMapping("salvar")
    public ResponseEntity<Void> salvarCliente(@RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = Cliente.builder()
                .nome(clienteRequest.getNome())
                .cpf(clienteRequest.getCpf())
                .enderecos(List.of(clienteRequest.getEndereco().toModel())) // Correção aqui
                .build();

        clienteService.criarNovo(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Void> editarCliente(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toModel();
        cliente.setId(id);
        clienteRepository.save(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}