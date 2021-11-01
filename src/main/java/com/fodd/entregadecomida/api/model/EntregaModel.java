package com.fodd.entregadecomida.api.model;

import com.fodd.entregadecomida.domain.model.StatusEntrega;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class EntregaModel {


    private Long id;
    private ClienteResumoModel cliente;
    private ProdutoModel produto;
    private DestinatarioModel destinatario;
    private BigDecimal frete;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
