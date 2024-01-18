package com.fundatec.petshop.controller.response;
import com.fundatec.petshop.model.Cliente;
import com.fundatec.petshop.model.Endereco;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Data
@Builder
public class ClienteResponse {

    private Long id;

    private String nome;

    private String cpf;

    private List<EnderecoResponse> endereco;

    public static ClienteResponse of(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .endereco(
                        List.of(EnderecoResponse.of((Endereco) cliente.getEnderecos()))
                )
                .build();
    }
}
