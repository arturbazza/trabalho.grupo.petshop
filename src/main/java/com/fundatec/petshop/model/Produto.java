package com.fundatec.petshop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoId;

    @Column
    private String nome;

    @Column
    private Integer valor;

    @Column
    private String descricao;

    @Column
    private Integer quantidadeAtual;

    @ManyToOne
    private TipoAnimal tipoAnimal;



}
