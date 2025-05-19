package com.example.demo.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record SpotResponseRecord(
    @JsonProperty("id") Long id,
    @JsonProperty("sector") String setor,
    @JsonProperty("lat") BigDecimal latitude,
    @JsonProperty("lng") BigDecimal longitude,
    @JsonProperty("occupied") Boolean status
) {
}
