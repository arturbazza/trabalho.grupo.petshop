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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//TESTE

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
    private AtendimentoRepository atendimentoRepository;
    private AtendimentoService atendimentoService;

    @PostMapping("/abrir")
    public String abrirAtendimento(@RequestBody Atendimento atendimento) {
        atendimentoService.abrirAtendimento(atendimento);
        return "Atendimento aberto com sucesso!";
    }

    @PostMapping("/finalizar")
    public Atendimento finalizarAtendimento(Long atendimentoId) {
        return atendimentoRepository.findById(atendimentoId).map(atendimento -> {
            atendimento.finalizar();
            return atendimentoRepository.save(atendimento);
        }).orElse(null);
    }

    @GetMapping("/listar")
    public List<AtendimentoResponse> listarAtendimento(@RequestParam(required = false) EstadoAtendimento ABERTO, @RequestParam(required = false, defaultValue = "nomeVeterinario") String sort, @RequestParam(required = false, defaultValue = "asc") String order, @RequestParam(required = false) String dataAberturaInicio, @RequestParam(required = false) String dataAberturaFim, @RequestParam(required = false) String dataEncerramentoInicio, @RequestParam(required = false) String dataEncerramentoFim) {
        return new ArrayList<>();
    }

    @PutMapping("/editar")
    public ResponseEntity<AtendimentoResponse> editarAtendimento(@PathVariable Long atendimentoId, @RequestBody AtendimentoRequest atendimentoRequest) {
        AtendimentoResponse atendimentoResponse = atendimentoService.editarAtendimento(atendimentoId, atendimentoRequest);
        return ResponseEntity.ok(atendimentoResponse);
    }


    @PostMapping("/adicionarProduto")
    public ResponseEntity<Void> adicionarProdutoAoAtendimento(@PathVariable Long atendimentoId, @RequestBody ProdutoRequest produtoRequest) {
        Atendimento atendimento = atendimentoRepository.findById(atendimentoId).orElse(null);

        if (atendimento != null) {
            Produto produto = Produto.builder().descricao(produtoRequest.getDescricao()).valor(produtoRequest.getValor()).build();

            atendimento.getProdutos().add(produto);
            atendimentoService.adicionarProduto(atendimento);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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








