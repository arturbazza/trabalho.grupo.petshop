package com.fundatec.petshop.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AtendimentoResponse {

private String nomeVeterinario;
private String nomeAtendente;
private Long id;
private Integer valorConsulta;
private Boolean pagamentoEfetuado;
private Date data;

}
