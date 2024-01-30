package com.fundatec.petshop.service;

import com.fundatec.petshop.model.Produto;
import com.fundatec.petshop.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void saveProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    @Transactional
    public void deleteProduto(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }

    @Transactional
    public void editProduto(Long produtoId, Produto produto) {
        Produto existingProduto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException(getProdutoNaoExisteMessage(produtoId)));
        existingProduto.setNome(produto.getNome());
        existingProduto.setDescricao(produto.getDescricao());
        existingProduto.setValor(produto.getValor());
        existingProduto.setQuantidadeAtual(produto.getQuantidadeAtual());

        produtoRepository.save(existingProduto);
    }

    @Transactional(readOnly = true)
    public Produto getProdutoById(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException(getProdutoNaoExisteMessage(produtoId)));
    }

    @Transactional(readOnly = true)
    public List<Produto> procurarProdutos(String nome, Integer precoMaiorQue, Integer precoMenorQue) {
        if (nome != null && precoMaiorQue != null && precoMenorQue != null) {
            return produtoRepository.findByNomeContainingAndValorBetween(nome, precoMaiorQue, precoMenorQue);
        } else if (nome != null) {
            return produtoRepository.findByNomeContaining(nome);
        } else if (precoMaiorQue != null && precoMenorQue != null) {
            return produtoRepository.findByValorBetween(precoMaiorQue, precoMenorQue);
        } else {
            return produtoRepository.findAll();
        }
    }

    private String getProdutoNaoExisteMessage(Long produtoId) {
        return "Produto com ID " + produtoId + " não existe.";
    }
}
