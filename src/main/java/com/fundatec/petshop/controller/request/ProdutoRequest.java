package com.fundatec.petshop.controller.request;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ProdutoRequest {

    private Integer valor;
    private String descricao;


}