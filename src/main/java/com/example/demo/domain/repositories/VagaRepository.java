package com.example.demo.domain.repositories;

import com.example.demo.domain.models.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    Optional<Vaga> findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    @Query("SELECT COUNT(v) FROM EntidadeVaga v WHERE v.garagem.setor = :setor AND v.situacao = 'OCUPADA'")
    long countOcupadasBySetor(@Param("setor") String setor);

    @Query("SELECT v FROM EntidadeVaga v WHERE v.garagem.id = :garagemId")
    List<Vaga> findByGaragemId(@Param("garagemId") Long garagemId);
}
