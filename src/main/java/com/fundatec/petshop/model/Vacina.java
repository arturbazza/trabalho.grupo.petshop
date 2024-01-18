package com.fundatec.petshop.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "vacinas")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Temporal(TemporalType.DATE)
    private LocalDate validade;

    @Column(length = 50, nullable = false)
    private String doencasAplicaveis;


    @ManyToOne
    private Mamifero mamifero;
}

