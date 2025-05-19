package com.example.demo.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ReceitaRequestRecord(
    @JsonProperty("date") LocalDate data,
    @JsonProperty("sector") String setor
) {
}
