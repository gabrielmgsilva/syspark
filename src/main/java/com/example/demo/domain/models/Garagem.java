package com.example.demo.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@EqualsAndHashCode
@Entity(name = "EntidadeGaragem")
@Table(name = "tbl_garagem", indexes = {@Index(name = "idx_garagem_id", columnList = "id", unique = true), @Index(name = "idx_garagem_setor", columnList = "setor", unique = true)})
public class Garagem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "setor", nullable = false, length = 2)
    private String setor;

    @Column(name = "preco_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoBase;

    @Column(name = "capacidade_maxima", nullable = false, length = 3)
    private Integer capacidade;

    @Column(name = "hora_abertura", nullable = false, length = 5)
    private String horaAbertura;

    @Column(name = "hora_fechamento", nullable = false, length = 5)
    private String horaFechamento;

    @Column(name = "duracao_limite", nullable = false, length = 3)
    private Integer duracaoLimite;

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

    public Garagem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public String getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(String horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    public Integer getDuracaoLimite() {
        return duracaoLimite;
    }

    public void setDuracaoLimite(Integer duracaoLimite) {
        this.duracaoLimite = duracaoLimite;
    }

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public LocalDateTime getDtUltimaModificacao() {
        return dtUltimaModificacao;
    }

    public void setDtUltimaModificacao(LocalDateTime dtUltimaModificacao) {
        this.dtUltimaModificacao = dtUltimaModificacao;
    }
}
