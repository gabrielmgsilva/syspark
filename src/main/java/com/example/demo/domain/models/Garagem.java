package com.example.demo.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EntidadeGaragem")
@Table(name = "tbl_garagem", indexes = {@Index(name = "idx_garagem_id", columnList = "id", unique = true), @Index(name = "idx_garagem_setor", columnList = "setor", unique = true)})
public class Garagem extends EntidadeBase{

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
}
