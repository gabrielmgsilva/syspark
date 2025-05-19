package com.example.demo.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlacaRequestRecord(
    @JsonProperty("license_plate") String placa
) {
}
