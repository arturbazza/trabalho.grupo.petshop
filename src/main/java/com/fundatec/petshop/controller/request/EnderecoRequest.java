package com.fundatec.petshop.controller.request;
import com.fundatec.petshop.model.Endereco;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class EnderecoRequest {
    private String logradouro;
    private Long numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco toModel() {
        return Endereco.builder()
                .logradouro(logradouro)
                .cidade(cidade)
                .bairro(bairro)
                .numero(numero)
                .build();
    }
}