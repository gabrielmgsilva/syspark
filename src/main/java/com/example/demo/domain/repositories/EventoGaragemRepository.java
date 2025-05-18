package com.example.demo.domain.repositories;

import com.example.demo.domain.models.EventoGaragem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventoGaragemRepository extends JpaRepository<EventoGaragem, Long> {

    @Query(value = "select e from EntidadeEventoGaragem e where e.placa = ?1 order by e.dtCriacao desc limit 1")
    EventoGaragem buscarUltimoEventoPorPlaca(String placa);
}
