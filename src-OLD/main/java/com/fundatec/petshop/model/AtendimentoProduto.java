package com.fundatec.petshop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "atendimento_produto")
public class AtendimentoProduto {
    @EmbeddedId
    private AtendimentoProdutoId id;

    @Column(nullable = false)
    private int quantidade;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataEntrada;

    @ManyToOne
    @MapsId("atendimentoId")
    @JoinColumn(name = "atendimentoId", referencedColumnName = "atendimentoId")
    private Atendimento atendimento;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produtoId", referencedColumnName = "produtoId")
    private Produto produto;

}
