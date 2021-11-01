package com.fodd.entregadecomida.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Erro {

    private Integer status;
    private LocalDateTime dataErro;
    private String titulo;
    private List<Campo> campo;

    @AllArgsConstructor
    @Data
    public static class Campo{
        private String nome;
        private String mensagem;
    }
}
