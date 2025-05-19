package com.example.demo.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StatusPlacaResponseRecord(
    @JsonProperty("license_plate") String placa,
    @JsonProperty("price_until_now") BigDecimal preco,
    @JsonProperty("entry_time") LocalDateTime dtEntrada,
    @JsonProperty("time_parked") LocalDateTime dtEstacionamento,
    @JsonProperty("lat") BigDecimal latitude,
    @JsonProperty("lng") BigDecimal longitude
) {
}
