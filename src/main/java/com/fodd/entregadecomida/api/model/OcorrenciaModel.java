package com.fodd.entregadecomida.api.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class OcorrenciaModel {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;
}
