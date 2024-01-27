package com.fundatec.petshop.controller;

import com.fundatec.petshop.controller.request.AtendimentoRequest;
import com.fundatec.petshop.controller.request.ProdutoRequest;
import com.fundatec.petshop.controller.response.AtendimentoResponse;
import com.fundatec.petshop.model.Atendimento;
import com.fundatec.petshop.model.EstadoAtendimento;
import com.fundatec.petshop.model.Pagamento;
import com.fundatec.petshop.model.Produto;
import com.fundatec.petshop.repository.AtendimentoRepository;
import com.fundatec.petshop.service.AtendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {
    private final AtendimentoRepository atendimentoRepository;
    private final AtendimentoService atendimentoService;

    @PostMapping("abrir")
    public String abrirAtendimento(@RequestBody @Valid AtendimentoRequest atendimentoRequest) {
        atendimentoService.abrirAtendimento(atendimentoRequest.toModel());
        return "Atendimento aberto com sucesso!";
    }

    @PostMapping("finalizar/{id}")
    public Atendimento finalizarAtendimento(Long atendimentoId) {
        return atendimentoRepository.findById(atendimentoId).map(atendimento -> {
            atendimento.finalizar();
            return atendimentoRepository.save(atendimento);
        }).orElse(null);
    }

    @GetMapping("listar")
    public List<AtendimentoResponse> listarAtendimento(@RequestParam(required = false) EstadoAtendimento ABERTO, @RequestParam(required = false, defaultValue = "nomeVeterinario") String sort, @RequestParam(required = false, defaultValue = "asc") String order, @RequestParam(required = false) String dataAberturaInicio, @RequestParam(required = false) String dataAberturaFim, @RequestParam(required = false) String dataEncerramentoInicio, @RequestParam(required = false) String dataEncerramentoFim) {
       List<Atendimento> atendimentos=atendimentoRepository.findAll();
        return atendimentos.stream()
                      .map(AtendimentoResponse::of)
                .toList();
    }

    @PatchMapping("editar")
    public ResponseEntity<AtendimentoResponse> editarAtendimento(@PathVariable Long atendimentoId, @RequestBody AtendimentoRequest atendimentoRequest) {
        AtendimentoResponse atendimentoResponse = atendimentoService.editarAtendimento(atendimentoId, atendimentoRequest);
        return ResponseEntity.ok(atendimentoResponse);
    }


  // Testar de esse metodo se está funcionando(está funcionando no postman mas não aparece na tabela)
    @PostMapping("/{id}/produtos")
    public void adicionarProdutos(@RequestBody AdicionarProdutoRequest adicionarProdutoRequest) {

    }

// Testar
    @DeleteMapping("/removerProduto/{atendimentoId}/{produtoId}")
    public ResponseEntity<Void> removerProdutoDoAtendimento(@PathVariable Long atendimentoId, @PathVariable Long produtoId) {
        Atendimento atendimento = atendimentoRepository.findById(atendimentoId).orElse(null);

        if (atendimento != null) {
            atendimento.getProdutos().removeIf(produto -> produto.getProdutoId().equals(produtoId));
            atendimentoService.removerProduto(atendimento);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/adicionarPagamento/{atendimentoId}")
    public ResponseEntity<Void> adicionarPagamentoAoAtendimento(@PathVariable Long atendimentoId, @RequestBody Pagamento pagamento) {
        Atendimento atendimento = atendimentoRepository.findById(atendimentoId).orElse(null);
        if (atendimento != null) {
            pagamento.setAtendimento(atendimento);
            atendimento.getPagamentos().add(pagamento);
            atendimentoService.adicionarPagamento(atendimento);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}








