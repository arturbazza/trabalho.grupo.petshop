package com.fundatec.petshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Integer valor;

    @Column(name = "datapagamento")
    @Temporal(TemporalType.DATE)
    private Date datapagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento")
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "atendimento_id")
    private Atendimento atendimento;
}
