package com.fundatec.petshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,nullable = false)
    private String nome ;

    @Column(length = 20,nullable = false)
    private Integer idade;

    @OneToOne
    private Cliente cliente;

    @ManyToOne
    private TipoAnimal tipoAnimal ;

}
