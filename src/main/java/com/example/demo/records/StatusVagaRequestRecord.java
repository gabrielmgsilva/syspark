package com.example.demo.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record StatusVagaRequestRecord(
    @JsonProperty("lat") BigDecimal latitude,
    @JsonProperty("lng") BigDecimal longitude
) {
}
