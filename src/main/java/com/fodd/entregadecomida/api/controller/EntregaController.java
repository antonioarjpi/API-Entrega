package com.fodd.entregadecomida.api.controller;

import com.fodd.entregadecomida.api.assembler.EntregaAssembler;
import com.fodd.entregadecomida.api.model.EntregaModel;
import com.fodd.entregadecomida.api.model.input.EntregaInput;
import com.fodd.entregadecomida.domain.model.Entrega;
import com.fodd.entregadecomida.domain.repository.EntregaRepository;
import com.fodd.entregadecomida.domain.service.FinalizacaoService;
import com.fodd.entregadecomida.domain.service.SolicitaEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitaEntregaService solicitaEntregaService;
    private EntregaRepository entregaRepository;
    private EntregaAssembler entregaAssembler;
    private FinalizacaoService finalizacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid  @RequestBody EntregaInput entregaInput){
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitaEntregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaModel> listar(){
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoService.finalizar(entregaId);
    }
}