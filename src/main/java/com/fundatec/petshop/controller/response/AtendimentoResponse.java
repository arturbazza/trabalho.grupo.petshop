package com.fundatec.petshop.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
private String nomeVeterinario;
private String nomeAtendente;
private Long atendimendoId;
private Integer valorConsulta;
private Boolean pagamentoEfetuado;
private Date data;

    public static AtendimentoResponse of(Atendimento atendimento) {
        return AtendimentoResponse.builder()
                .nomeVeterinario(atendimento.getNomeVeterinario())
                .nomeAtendente(atendimento.getNomeAtendente())
                .atendimendoId(atendimento.getAtendimentoId())
                .valorConsulta(atendimento.getValorConsulta())
                .pagamentoEfetuado(atendimento.getPagamentoEfetuado())
                .data(atendimento.getData())
                .build();
    }
}
