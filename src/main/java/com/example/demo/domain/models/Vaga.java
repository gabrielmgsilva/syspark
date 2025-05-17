package com.example.demo.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EntidadeSetor")
@Table(name = "tbl_setor", indexes = {@Index(name = "idx_setor_id", columnList = "id", unique = true), @Index(name = "idx_localizacao_vaga", columnList = "latitude, longitude", unique = true)})
public class Vaga extends EntidadeBase{

    @Column(name = "latitude", nullable = false, precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 10, scale = 6)
    private BigDecimal longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "garagem_id", nullable = false)
    private Garagem garagem;
}
