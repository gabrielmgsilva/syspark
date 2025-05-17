package com.example.demo.domain.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dt_criacao", nullable = false, updatable = false)
    private LocalDateTime dtCriacao;

    @Column(name = "dt_ultima_modificacao")
    private LocalDateTime dtUltimaModificacao;


    @PrePersist
    protected void onCreate() {
        dtCriacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dtUltimaModificacao = LocalDateTime.now();
    }
}
