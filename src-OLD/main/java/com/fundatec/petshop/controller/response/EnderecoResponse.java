package com.fundatec.petshop.controller.response;

import com.fundatec.petshop.model.Endereco;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class EnderecoResponse {
    private Long id;
    private String logradouro;
    private Long numero;
    private String bairro;
    private String cidade;
    private String estado;


    public static EnderecoResponse of(Endereco endereco) {
        return EnderecoResponse.builder()
                .id(endereco.getId())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .logradouro(endereco.getLogradouro())
                .build();
    }
}

