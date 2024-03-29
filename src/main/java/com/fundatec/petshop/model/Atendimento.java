package com.fundatec.petshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atendimentos")
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atendimentoId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(length = 10)
    private String nomeAtendente;

    @Column(length = 30)
    private String nomeVeterinario;

    @Column(nullable = false)
    private Boolean pagamentoEfetuado = false;

    @Column(nullable = false)
    private Integer valorConsulta;

    private EstadoAtendimento estadoAtendimento;

    public void finalizar() {
        this.estadoAtendimento = EstadoAtendimento.FECHADO;
    }


    @ManyToOne
    private Unidade unidade;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Pet pet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "atendimento_id")
    @Builder.Default
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "atendimento_id")
    @Builder.Default
    private List<Pagamento> pagamentos = new ArrayList<>();


}