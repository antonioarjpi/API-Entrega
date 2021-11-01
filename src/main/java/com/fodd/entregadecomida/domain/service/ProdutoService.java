package com.fodd.entregadecomida.domain.service;


import com.fodd.entregadecomida.domain.exception.DomainException;
import com.fodd.entregadecomida.domain.model.Produto;
import com.fodd.entregadecomida.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;


    @Transactional
    public Produto adicionarProduto(Produto produto){
        boolean produtoEmUso = produtoRepository.findByNome(produto.getNome())
                .stream()
                .anyMatch(produtoExistente -> !produtoExistente.equals(produto));
        if (produtoEmUso){
            throw new DomainException("Produto já cadastrado");
        }
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deletar(Long produtoId){
       produtoRepository.deleteById(produtoId);
    }
}
