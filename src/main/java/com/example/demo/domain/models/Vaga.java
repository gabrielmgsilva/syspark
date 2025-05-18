package com.example.demo.domain.models;

import com.example.demo.domain.enums.SituacaoVaga;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EntidadeVaga")
@Table(name = "tbl_vaga", indexes = {@Index(name = "idx_vaga_id", columnList = "id", unique = true), @Index(name = "idx_localizacao_vaga", columnList = "latitude, longitude", unique = true)})public class Vaga extends EntidadeBase{

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
}
