package com.example.aula3.repository;

import java.util.List;

import com.example.aula3.entity.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByNomeLike(String nome);

    List<Produto> findByQtdLessThanEqual(Integer qtd);

    List<Produto> findNomeByOrderByNomeAsc();

    List<Produto> findTop10ByOrderByQtdDesc();
}
