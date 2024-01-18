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

    @Autowired
    private ClienteRepository clienteRepository;
    @GetMapping("listar")
    public List<ClienteResponse> listaClientes(@RequestParam(required = false) String nome) {
        List<Cliente> clientes = clienteRepository.findAll(); // Utiliza o clienteRepository para obter todos os clientes
        return clientes.stream()
                .map(ClienteResponse::of)
                .toList();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criarNovo(@RequestBody ClienteRequest clienteRequest) {
        Cliente model = clienteRequest.toModel();
        Cliente salvo = clienteRepository.save(model);  // Utiliza o clienteRepository para salvar o cliente
        return ClienteResponse.of(salvo);
    }
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);  // Utiliza o clienteRepository para deletar o cliente
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Void> editarCliente(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toModel();
        cliente.setId(id);
        clienteRepository.save(cliente);  // Utiliza o clienteRepository para editar o cliente
        return new ResponseEntity<>(HttpStatus.OK);
    }


}