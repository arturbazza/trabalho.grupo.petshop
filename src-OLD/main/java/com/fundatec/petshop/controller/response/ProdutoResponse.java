package com.fundatec.petshop.controller.response;

import com.fundatec.petshop.model.Produto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class ProdutoResponse {
    private String nome;
    private Integer valor;
    private String descricao;
    private Integer quantidadeAtual;

    public static ProdutoResponse of(Produto produto) {
        return ProdutoResponse.builder()
                .nome(produto.getNome())
                .valor(produto.getValor())
                .descricao(produto.getDescricao())
                .quantidadeAtual(produto.getQuantidadeAtual())
                .build();
    }
}
