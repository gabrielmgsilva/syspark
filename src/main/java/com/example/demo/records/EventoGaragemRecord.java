package com.example.demo.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventoGaragemRecord(
    @JsonProperty("license_plate") String placa,
    @JsonProperty("entry_time") LocalDateTime dtEntrada,
    @JsonProperty("event_type") String tipoEvento,
    @JsonProperty("exit_time") LocalDateTime dtSaida,
    @JsonProperty("lat") BigDecimal latitude,
    @JsonProperty("lng") BigDecimal longitude
    ) {
}
