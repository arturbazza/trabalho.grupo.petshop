package com.fundatec.petshop.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quantidade_estoque")
public class QuantidadeEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantidadeAtual;

    @OneToOne
    private Produto produto;

}
