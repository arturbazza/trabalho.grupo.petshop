package com.fundatec.petshop.controller.request;

import com.fundatec.petshop.model.Cliente;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ClienteRequest {

    private String cpf;
    private String nome;

    private EnderecoRequest endereco;

    public Cliente toModel() {
        return Cliente.builder()
                .nome(nome)
                .cpf(cpf)
                .endereco(endereco.toModel())
                .build();
    }
}
