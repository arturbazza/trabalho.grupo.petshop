package com.fundatec.petshop.controller.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class ProdutoResponse {
    private Integer valor;
    private String nome;
    private String descricao;
    private Integer quantidadeAtual;
}

