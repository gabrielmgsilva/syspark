package com.example.demo.domain.repositories;

import com.example.demo.domain.models.Garagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GaragemRepository extends JpaRepository<Garagem, Long> {
}
