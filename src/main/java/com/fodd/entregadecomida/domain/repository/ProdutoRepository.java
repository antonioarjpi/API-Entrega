package com.fodd.entregadecomida.domain.repository;

import com.fodd.entregadecomida.domain.model.Cliente;
import com.fodd.entregadecomida.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);
}
