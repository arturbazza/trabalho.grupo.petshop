package com.fundatec.petshop.repository;

import com.fundatec.petshop.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContaining(String nome);
    List<Produto> findByValorBetween(Integer precoMaiorQue, Integer precoMenorQue);
    List<Produto> findByNomeContainingAndValorBetween(String nome, Integer precoMaiorQue, Integer precoMenorQue);
}
