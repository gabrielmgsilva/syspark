package com.example.demo.domain.models;

import com.example.demo.domain.enums.SituacaoVaga;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EntidadeVaga")
@Table(name = "tbl_vaga", indexes = {@Index(name = "idx_vaga_id", columnList = "id", unique = true), @Index(name = "idx_localizacao_vaga", columnList = "latitude, longitude", unique = true)})
public class Vaga{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude", nullable = false, precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "situacao", nullable = false, length = 20, columnDefinition = "DEFAULT 'LIVRE'")
    @Enumerated(EnumType.STRING)
    private SituacaoVaga situacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "garagem_id", nullable = false)
    private Garagem garagem;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public SituacaoVaga getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoVaga situacao) {
        this.situacao = situacao;
    }

    public Garagem getGaragem() {
        return garagem;
    }

    public void setGaragem(Garagem garagem) {
        this.garagem = garagem;
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
