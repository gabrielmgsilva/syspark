package com.example.demo.domain.repositories;

import com.example.demo.domain.models.EventoGaragem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EventoGaragemRepository extends JpaRepository<EventoGaragem, Long> {

    @Query(value = "select e from EntidadeEventoGaragem e where e.placa = :placa order by e.dtCriacao desc limit 1")
    EventoGaragem buscarUltimoEventoPorPlaca(@Param("placa") String placa);

    @Query("SELECT e FROM EntidadeEventoGaragem e WHERE e.vaga.id = :vagaId AND e.tipoEvento = 'PARKED' ORDER BY e.dtCriacao DESC")
    Optional<EventoGaragem> findLastParkedEventByVagaId(@Param("vagaId") Long vagaId);

    @Query("SELECT e FROM EntidadeEventoGaragem e WHERE e.placa = :placa ORDER BY e.dtCriacao DESC")
    Optional<EventoGaragem> findFirstByPlacaOrderByDtCriacaoDesc(@Param("placa") String placa);
}
