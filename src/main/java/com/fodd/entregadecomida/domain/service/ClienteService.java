package com.fodd.entregadecomida.domain.service;

import com.fodd.entregadecomida.domain.exception.DomainException;
import com.fodd.entregadecomida.domain.model.Cliente;
import com.fodd.entregadecomida.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new DomainException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if (emailEmUso){
            throw new DomainException("Esse e-mail ja está cadastrado.");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletar(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
