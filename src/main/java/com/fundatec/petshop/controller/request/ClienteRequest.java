package com.fundatec.petshop.controller.request;

import com.fundatec.petshop.model.Cliente;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class ClienteRequest {

    private String nome;
    private String cpf;
    private EnderecoRequest endereco;

    public Cliente toModel() {
        return Cliente.builder()
                .nome(nome)
                .cpf(cpf)
                .enderecos(List.of(endereco.toModel()))
                .build();
    }
}
