package com.fundatec.petshop.controller;

import com.fundatec.petshop.controller.request.ClienteRequest;
import com.fundatec.petshop.controller.response.ClienteResponse;
import com.fundatec.petshop.model.Cliente;
import com.fundatec.petshop.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    private final List<Cliente> clientes = new ArrayList<>();

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("listar")
    public List<ClienteResponse> listaClientes(@RequestParam(required = false) String nome) {

        return clientes.stream()
                .map(ClienteResponse::of)
                .toList();
    }

    @PostMapping("criar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarCliente(@RequestBody ClienteRequest clienteRequest) {
        this.clientes.add(clienteRequest.toModel());
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Void> editarCliente(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = Cliente.builder()
                .nome(clienteRequest.getNome())
                .cpf(clienteRequest.getCpf())
                .endereco(clienteRequest.toModel().getEndereco())
                .build();
        clienteService.editCliente(id, cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}