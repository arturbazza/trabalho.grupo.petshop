package com.fundatec.petshop.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoId;

    @Column(nullable = false)
    private Integer valor;

    @Column(length = 50, nullable = true)
    private String nome;

    @Column(length = 20, nullable = false)
    private String descricao;

    @Column(nullable = true)
    private Integer quantidadeAtual;

    @ManyToOne
    private TipoAnimal tipoAnimal;


}
