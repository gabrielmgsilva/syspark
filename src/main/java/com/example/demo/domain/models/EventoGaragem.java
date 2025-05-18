package com.example.demo.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EntidadeEventoGaragem")
@Table(name = "tbl_evento_garagem")
public class EventoGaragem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", nullable = false, length = 8)
    private String placa;

    @Column(name = "tipo_evento", nullable = false, length = 20)
    private String tipoEvento;

    @JoinColumn(name = "vaga_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vaga vaga;

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

    public EventoGaragem(String placa, String tipoEvento) {
        this.placa = placa;
        this.tipoEvento = tipoEvento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
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
