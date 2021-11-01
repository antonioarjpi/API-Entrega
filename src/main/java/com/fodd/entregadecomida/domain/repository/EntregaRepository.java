package com.fodd.entregadecomida.domain.repository;

import com.fodd.entregadecomida.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

}
