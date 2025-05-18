package com.example.demo.domain.repositories;

import com.example.demo.domain.models.EventoGaragem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoGaragemRepository extends JpaRepository<EventoGaragem, Long> {
}
