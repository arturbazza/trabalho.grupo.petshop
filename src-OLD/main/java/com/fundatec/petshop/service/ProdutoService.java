package com.fundatec.petshop.service;

import com.fundatec.petshop.model.Produto;
import com.fundatec.petshop.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void saveProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public void deleteProduto(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }

    public void editProduto(Long produtoId, Produto produto) {
        produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException(produtoId + " não existe"));

        produto.setProdutoId(produtoId);
        produtoRepository.save(produto);
    }

    public Produto getProdutoId(Long produtoId) {
        return this.produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException(produtoId + " não existe"));
    }
}
