package com.fundatec.petshop.controller;

import com.fundatec.petshop.controller.request.AdicionarProdutoRequest;
import com.fundatec.petshop.controller.request.AtendimentoRequest;
import com.fundatec.petshop.controller.response.AtendimentoResponse;
import com.fundatec.petshop.model.Atendimento;
import com.fundatec.petshop.model.Pagamento;
import com.fundatec.petshop.repository.AtendimentoRepository;
import com.fundatec.petshop.service.AtendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<AtendimentoResponse> finalizarAtendimento(@PathVariable Long id) {
        return atendimentoRepository.findById(id)
                .map(atendimento -> {
                    atendimento.finalizar();
                    return ResponseEntity.ok(AtendimentoResponse.of(atendimento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("listar")
    public ResponseEntity<List<AtendimentoResponse>> listarAtendimento() {
        List<Atendimento> atendimentos = atendimentoRepository.findAll();
        List<AtendimentoResponse> responseList = atendimentos.stream()
                .map(AtendimentoResponse::of)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @PatchMapping("editar/{atendimentoId}")
    public ResponseEntity<AtendimentoResponse> editarAtendimento(@PathVariable Long atendimentoId, @RequestBody AtendimentoRequest atendimentoRequest) {
        AtendimentoResponse atendimentoResponse = atendimentoService.editarAtendimento(atendimentoId, atendimentoRequest);
        if (atendimentoResponse != null) {
            return ResponseEntity.ok(atendimentoResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("{id}/produtos")
    public void adicionarProdutos(@RequestBody AdicionarProdutoRequest adicionarProdutoRequest) {

    }

// Testar
@DeleteMapping("/remover{atendimentoId}/{produtoId}")
public ResponseEntity<Void> removerProdutoDoAtendimento(@PathVariable Long atendimentoId, @PathVariable Long produtoId) {
    Optional<Atendimento> optionalAtendimento = atendimentoRepository.findById(atendimentoId);

    if (optionalAtendimento.isPresent()) {
        Atendimento atendimento = optionalAtendimento.get();
        atendimento.getProdutos().removeIf(produto -> produto.getProdutoId().equals(produtoId));
        atendimentoRepository.save(atendimento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
    @PostMapping("/adicionarPagamento/{atendimentoId}")
    public ResponseEntity<Void> adicionarPagamentoAoAtendimento(@PathVariable Long atendimentoId, @RequestBody Pagamento pagamento) {
        atendimentoService.adicionarPagamento(atendimentoId, pagamento);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}








