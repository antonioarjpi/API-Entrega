package com.fodd.entregadecomida.api.controller;

import com.fodd.entregadecomida.domain.model.Cliente;
import com.fodd.entregadecomida.domain.repository.ClienteRepository;
import com.fodd.entregadecomida.domain.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }


    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }


        @DeleteMapping("/{clienteId}")
        public ResponseEntity<Void> deletar(@PathVariable  Long clienteId){
            if (!clienteRepository.existsById(clienteId)) {
                return ResponseEntity.notFound().build();
            }
            clienteService.deletar(clienteId);
            return ResponseEntity.noContent().build();
        }
    }

