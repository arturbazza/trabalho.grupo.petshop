package com.fundatec.petshop.controller;
import com.fundatec.petshop.model.Produto;
import lombok.Data;

import java.util.List;

@Data
    public class AdicionarProdutoRequest {
        private List<Produto> produtos;
    }

