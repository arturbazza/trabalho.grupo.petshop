package com.fundatec.petshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "estoques")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOperacao;

    private TipoOperacao tipoOperacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validade;

    @Column(length = 100,nullable = false)
    private String lote;

@ManyToOne
    private Produto produto;
}
