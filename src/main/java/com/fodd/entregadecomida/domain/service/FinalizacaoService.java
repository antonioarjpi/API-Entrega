package com.fodd.entregadecomida.domain.service;

import com.fodd.entregadecomida.domain.model.Entrega;
import com.fodd.entregadecomida.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoService {

    private EntregaRepository entregaRepository;
    private BuscarEntregaService buscarEntregaService;

    @Transactional
    public void finalizar(Long entregaId){
        Entrega entrega = buscarEntregaService.buscar(entregaId);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }
}

