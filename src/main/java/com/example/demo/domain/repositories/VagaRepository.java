package com.example.demo.domain.repositories;

import com.example.demo.domain.models.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    Optional<Vaga> findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
