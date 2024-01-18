package com.fundatec.petshop.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Builder
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private Long numero;

    @Column(length = 50)
    private String logradouro;

    @Column(length = 20)
    private String bairro;

    @Column(length = 20)
    private String cidade;

    @OneToOne
    private Cliente cliente;

}
