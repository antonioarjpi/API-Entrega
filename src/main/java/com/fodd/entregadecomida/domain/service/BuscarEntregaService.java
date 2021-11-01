package com.fodd.entregadecomida.domain.service;

import com.fodd.entregadecomida.domain.exception.DomainException;
import com.fodd.entregadecomida.domain.model.Entrega;
import com.fodd.entregadecomida.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BuscarEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId){
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new DomainException("Ocorrência não encontrada"));
    }
}
