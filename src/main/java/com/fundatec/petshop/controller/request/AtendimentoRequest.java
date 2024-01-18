package com.fundatec.petshop.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@Data
public class AtendimentoRequest {
    private Date data;
    private String nomeAtendente;
    private Boolean pagamentoEfetuado;
    private Integer valorConsulta;

    public Atendimento toModel() {
        return Atendimento.builder()
                .data(data)
                .nomeAtendente(nomeAtendente)
                .pagamentoEfetuado(pagamentoEfetuado)
                .valorConsulta(valorConsulta)
                .build();
    }
}
