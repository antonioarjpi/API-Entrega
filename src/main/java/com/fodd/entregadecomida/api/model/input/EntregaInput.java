package com.fodd.entregadecomida.api.model.input;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EntregaInput {
    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private ProdutoInput produto;

    @Valid
    @NotNull
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal frete;
}
