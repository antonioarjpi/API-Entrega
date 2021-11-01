package com.fodd.entregadecomida.domain.service;

import com.fodd.entregadecomida.domain.exception.DomainException;
import com.fodd.entregadecomida.domain.model.Cliente;
import com.fodd.entregadecomida.domain.model.Entrega;
import com.fodd.entregadecomida.domain.model.Produto;
import com.fodd.entregadecomida.domain.model.StatusEntrega;
import com.fodd.entregadecomida.domain.repository.ClienteRepository;
import com.fodd.entregadecomida.domain.repository.EntregaRepository;
import com.fodd.entregadecomida.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class SolicitaEntregaService {

    private ClienteRepository clienteRepository;
    private EntregaRepository entregaRepository;
    private ProdutoRepository produtoRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega){
        Cliente cliente = clienteRepository.findById(entrega.getCliente().getId())
                        .orElseThrow(() -> new DomainException("Cliente não encontrado"));
        Produto produto = produtoRepository.findById(entrega.getProduto().getId())
                .orElseThrow(() -> new DomainException("Produto não encontrado"));
        entrega.setCliente(cliente);
        entrega.setProduto(produto);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }
}
