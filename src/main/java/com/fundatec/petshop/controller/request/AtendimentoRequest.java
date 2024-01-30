package com.fundatec.petshop.controller.request;

import com.fundatec.petshop.model.Atendimento;
import com.fundatec.petshop.model.Cliente;
import com.fundatec.petshop.model.Unidade;
import jakarta.validation.constraints.NotNull;
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
    private String nomeVeterinario;
    private Cliente id_cliente;
    private Unidade id_unidade;
    @NotNull
    private Boolean pagamentoEfetuado;
    private Integer valorConsulta;

    public Atendimento toModel() {
        return Atendimento.builder()
                .data(data)
                .nomeAtendente(nomeAtendente)
                .nomeVeterinario(nomeVeterinario)
                .pagamentoEfetuado(pagamentoEfetuado)
                .valorConsulta(valorConsulta)
                .cliente(id_cliente)
                .unidade(id_unidade)
                .build();
    }
}
