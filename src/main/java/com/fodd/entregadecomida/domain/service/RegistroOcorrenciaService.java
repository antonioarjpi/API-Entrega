package com.fodd.entregadecomida.domain.service;

import com.fodd.entregadecomida.domain.model.Entrega;
import com.fodd.entregadecomida.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscarEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        return entrega.adicionarOcorrencia(descricao);
    }

}
