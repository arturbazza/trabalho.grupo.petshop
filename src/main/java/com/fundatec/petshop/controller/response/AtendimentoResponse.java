package com.fundatec.petshop.controller.response;
import com.fundatec.petshop.model.Atendimento;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
@Builder
public class AtendimentoResponse {
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
