package com.fundatec.petshop.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Builder
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String cpf;

    @Column(length = 20, nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "", columnDefinition = "string")
    private Endereco endereco;

}
