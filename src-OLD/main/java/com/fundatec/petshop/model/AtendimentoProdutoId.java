package com.fundatec.petshop.model;

import jakarta.persistence.Column;

public class AtendimentoProdutoId {
    @Column(name= "produtoId")
    private Long produtoId;

    @Column(name = "atendimentoId")
    private Long atendimentoId;
}
