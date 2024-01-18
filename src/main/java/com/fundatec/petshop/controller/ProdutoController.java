package com.fundatec.petshop.controller;

import com.fundatec.petshop.controller.request.ProdutoRequest;
import com.fundatec.petshop.controller.response.ProdutoResponse;
import com.fundatec.petshop.model.Produto;
import com.fundatec.petshop.service.ProdutoService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@RestController
@RequestMapping(path = "produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("listar")
    public List<ProdutoResponse> producurarProdutos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer precoMaiorQue,
            @RequestParam(required = false) Integer precoMenorQue
    ) {
        return new ArrayList<>();
    }

    @PostMapping("salvar")
    public ResponseEntity<Void> salvarProduto(@RequestBody ProdutoRequest produtoRequest) {
        Produto produto = Produto.builder()
                .descricao(produtoRequest.getDescricao())
                .valor(produtoRequest.getValor())
                .build();

        produtoService.saveProduto(produto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long produtoId) {
        produtoService.deleteProduto(produtoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Void> editarProduto(@PathVariable Long produtoId, @RequestBody ProdutoRequest produtoRequest) {
        Produto produto = Produto.builder()
                .descricao(produtoRequest.getDescricao())
                .valor(produtoRequest.getValor())
                .build();

        produtoService.editProduto(produtoId, produto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}




