package com.example.demo.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record GaragemResponseRecord(
    @JsonProperty("sector") String setor,
    @JsonProperty("base_price") BigDecimal precoBase,
    @JsonProperty("max_capacity") Integer capacidade,
    @JsonProperty("open_hour") String horarioAbertura,
    @JsonProperty("close_hour") String horarioFechamento,
    @JsonProperty("duration_limit_minutes") Integer duracaoLimite,
    @JsonProperty("spots") List<SpotResponseRecord> vagas
) {

}
