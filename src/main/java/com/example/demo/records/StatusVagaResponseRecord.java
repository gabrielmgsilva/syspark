package com.example.demo.records;

import java.time.LocalDateTime;

public record StatusVagaResponseRecord(
    Boolean ocupada,
    LocalDateTime dtEntrada,
    LocalDateTime dtEstacionamento
) {
}
