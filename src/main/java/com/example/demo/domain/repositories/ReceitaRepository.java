package com.example.demo.domain.repositories;

import com.example.demo.domain.models.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    @Query("SELECT COALESCE(SUM(r.valor), 0) AS amount " +
        "FROM EntidadeReceita r " +
        "WHERE r.garagem.setor = :sector " +
        "AND DATE(r.dtCriacao) = :date")
    BigDecimal findTotalAmountBySectorAndDate(@Param("sector") String sector, @Param("date") LocalDate date);
}
