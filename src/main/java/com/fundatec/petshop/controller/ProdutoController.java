package com.fundatec.petshop.controller;

import com.fundatec.petshop.controller.request.ProdutoRequest;
import com.fundatec.petshop.controller.response.ProdutoResponse;
import com.fundatec.petshop.model.Produto;
import com.fundatec.petshop.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> procurarProdutos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer precoMaiorQue,
            @RequestParam(required = false) Integer precoMenorQue
    ) {
        List<Produto> produtos = produtoService.procurarProdutos(nome, precoMaiorQue, precoMenorQue);
        List<ProdutoResponse> responseList = produtos.stream()
                .map(ProdutoResponse::of)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    public ResponseEntity<Void> salvarProduto(@RequestBody ProdutoRequest produtoRequest) {
        Produto produto = produtoRequest.toModel();
        produtoService.saveProduto(produto);
        return ResponseEntity.created(URI.create("/produtos/" + produto.getProdutoId())).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> editarProduto(@PathVariable Long id, @RequestBody ProdutoRequest produtoRequest) {
        Produto produto = produtoRequest.toModel();
        produtoService.editProduto(id, produto);
        return ResponseEntity.ok().build();
    }
}
