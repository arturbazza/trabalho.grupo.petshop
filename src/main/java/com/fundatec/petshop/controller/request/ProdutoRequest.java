package com.fundatec.petshop.controller.request;
import com.fundatec.petshop.model.Produto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ProdutoRequest {
    private String nome;
    private Integer valor;
    private String descricao;
    private Integer quantidadeAtual;
    public Produto toModel() {
        return Produto.builder()
                .nome(nome)
                .valor(valor)
                .descricao(descricao)
                .quantidadeAtual(quantidadeAtual)
                .build();
    }

}
