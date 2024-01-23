package com.fundatec.petshop.controller.request;

import com.fundatec.petshop.model.Cliente;
import lombok.Data;

import java.util.List;

@Data
public class ClienteRequest {

    private String nome;
    private String cpf;
    private EnderecoRequest endereco;

    public Cliente toModel() {
        Cliente cliente = Cliente.builder()
                .nome(nome)
                .cpf(cpf)
                .build();

        if (endereco != null) {
            cliente.setEnderecos(List.of(endereco.toModel()));
        }

        return cliente;
    }
}
