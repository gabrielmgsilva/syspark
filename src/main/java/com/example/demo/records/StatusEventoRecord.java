package com.example.demo.records;

import com.example.demo.domain.models.EventoGaragem;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record StatusEventoRecord(
    @JsonProperty("event_id") Long idEvento,
    @JsonProperty("plate") String placa,
    @JsonProperty("event_date") LocalDateTime dtEvento,
    @JsonProperty("event_type") String tipoEvento
) {

    public static StatusEventoRecord build(Long id, String placa, LocalDateTime dtCriacao, String tipoEvento) {
        return new StatusEventoRecord(id, placa, dtCriacao, tipoEvento);
    }
}
