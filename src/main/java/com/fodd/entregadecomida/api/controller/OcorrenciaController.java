package com.fodd.entregadecomida.api.controller;

import com.fodd.entregadecomida.api.assembler.OcorrenciaAssembler;
import com.fodd.entregadecomida.api.model.OcorrenciaModel;
import com.fodd.entregadecomida.api.model.input.OcorrenciaInput;
import com.fodd.entregadecomida.domain.model.Entrega;
import com.fodd.entregadecomida.domain.model.Ocorrencia;
import com.fodd.entregadecomida.domain.service.BuscarEntregaService;
import com.fodd.entregadecomida.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private BuscarEntregaService buscarEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId,
                                     @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {

        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
                .registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscarEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }

}