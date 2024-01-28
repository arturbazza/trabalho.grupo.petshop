package com.fundatec.petshop.service;

import com.fundatec.petshop.controller.request.AtendimentoRequest;
import com.fundatec.petshop.controller.request.ProdutoRequest;
import com.fundatec.petshop.controller.response.AtendimentoResponse;
import com.fundatec.petshop.model.Atendimento;
import com.fundatec.petshop.model.Pagamento;
import com.fundatec.petshop.model.Produto;
import com.fundatec.petshop.repository.AtendimentoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }


    public Atendimento abrirAtendimento(Atendimento atendimento) {
        atendimentoRepository.save(atendimento);
        return atendimento;
    }


    public AtendimentoResponse editarAtendimento(Long atendimentoId, AtendimentoRequest atendimentoRequest) {
        Optional<Atendimento> existOptional = atendimentoRepository.findById(atendimentoId);

        if (existOptional.isPresent()) {
            Atendimento exist = existOptional.get();

            exist.setPagamentoEfetuado(atendimentoRequest.getPagamentoEfetuado());
            exist.setData(atendimentoRequest.getData());
            exist.setNomeAtendente(atendimentoRequest.getNomeAtendente());
            exist.setValorConsulta(atendimentoRequest.getValorConsulta());

            Atendimento atualizado = atendimentoRepository.save(exist);

            return AtendimentoResponse.builder()
                    .pagamentoEfetuado(atualizado.getPagamentoEfetuado())
                    .data(atualizado.getData())
                    .nomeAtendente(atualizado.getNomeAtendente())
                    .nomeVeterinario(atualizado.getNomeVeterinario())
                    .valorConsulta(atualizado.getValorConsulta())
                    .build();
        } else {
            return null;
        }
    }

    public void adicionarProduto(Long atendimentoId, ProdutoRequest produtoRequest) {
        Optional<Atendimento> atendimentoOptional = atendimentoRepository.findById(atendimentoId);

        if (atendimentoOptional.isPresent()) {
            Atendimento atendimento = atendimentoOptional.get();
            Produto produto = produtoRequest.toModel();
            atendimento.getProdutos().add(produto);
            atendimentoRepository.save(atendimento);
        }
    }

    public void removerProduto(Long atendimentoId, Long produtoId) {
        Optional<Atendimento> atendimentoOptional = atendimentoRepository.findById(atendimentoId);

        if (atendimentoOptional.isPresent()) {
            Atendimento atendimento = atendimentoOptional.get();
            atendimento.getProdutos().removeIf(produto -> produto.getProdutoId().equals(produtoId));
            atendimentoRepository.save(atendimento);
        }
    }

    public void adicionarPagamento(Long atendimentoId, Pagamento pagamento) {
        Optional<Atendimento> atendimentoOptional = atendimentoRepository.findById(atendimentoId);

        if (atendimentoOptional.isPresent()) {
            Atendimento atendimento = atendimentoOptional.get();
            atendimento.getPagamentos().add(pagamento);
            atendimentoRepository.save(atendimento);
        }
    }
}
