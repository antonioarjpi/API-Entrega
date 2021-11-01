package com.fodd.entregadecomida.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fodd.entregadecomida.api.exceptionHandler.ValidationsGroups;
import com.fodd.entregadecomida.domain.exception.DomainException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationsGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @Valid
    @NotNull
    @ManyToOne
    private Produto produto;


    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    @Valid
    @NotNull
    @Embedded
    private Destinatario destinatario;

    @NotNull
    private BigDecimal frete;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;


    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);
        this.getOcorrencias().add(ocorrencia);
        return ocorrencia;
    }

    public void finalizar() {
        if(!StatusEntrega.PENDENTE.equals(getStatus())){
            throw new DomainException("Entrega não pode ser finalizada");
        }
        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }
}
