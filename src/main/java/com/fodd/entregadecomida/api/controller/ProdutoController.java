package com.fodd.entregadecomida.api.controller;

import com.fodd.entregadecomida.domain.model.Produto;
import com.fodd.entregadecomida.domain.repository.ProdutoRepository;
import com.fodd.entregadecomida.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionarProduto(@Valid @RequestBody Produto produto){
        return produtoService.adicionarProduto(produto);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualiza(@PathVariable Long produtoId, @RequestBody Produto produto){
        if (!produtoRepository.existsById(produtoId)){
            return ResponseEntity.notFound().build();
        }
        produto.setId(produtoId);
        produto = produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long produtoId){
        if(!produtoRepository.existsById(produtoId)){
            return ResponseEntity.notFound().build();
        }
        produtoService.deletar(produtoId);
        return ResponseEntity.notFound().build();
    }

}
