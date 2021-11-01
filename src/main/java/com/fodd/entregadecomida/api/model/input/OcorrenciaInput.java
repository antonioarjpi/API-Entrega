package com.fodd.entregadecomida.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OcorrenciaInput {

    @NotBlank
    private String descricao;

}
