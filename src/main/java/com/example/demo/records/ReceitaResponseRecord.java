package com.example.demo.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReceitaResponseRecord(
    @JsonProperty("amount") BigDecimal valor,
    @JsonProperty("currency") String moeda,
    @JsonProperty("timestamp") LocalDateTime data
) {
}
