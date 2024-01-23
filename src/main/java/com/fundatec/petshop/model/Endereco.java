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

    @Column
    private String logradouro;
    @Column
    private Long numero;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String estado;

    @OneToOne
    private Cliente cliente;

}
