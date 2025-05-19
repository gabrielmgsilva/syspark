package com.example.demo.controllers;

import com.example.demo.records.*;
import com.example.demo.services.GaragemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Garagem API", description = "Endpoints para gerenciamento de garagens e receitas")
public class AppController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private GaragemService garagemService;

    public AppController(GaragemService garagemService) {
        log.info("WebhookController initialized");
        this.garagemService = garagemService;
    }

    @Operation(summary = "Registrar evento na garagem", description = "Registra um evento relacionado à garagem.")
    @ApiResponse(responseCode = "200", description = "Evento registrado com sucesso.")
    @PostMapping("/webhook")
    public ResponseEntity<StatusEventoRecord> eventoGaragem(
        @Parameter(description = "Dados do evento da garagem", required = true) @RequestBody EventoGaragemRecord evento) {
        log.info(evento.toString());
        return ResponseEntity.ok().body(garagemService.eventoGaragem(evento));
    }

    @Operation(summary = "Listar garagens", description = "Retorna a configuração de todas as garagens.")
    @ApiResponse(responseCode = "200", description = "Lista de garagens retornada com sucesso.")
    @GetMapping("/garage")
    public ResponseEntity<List<GaragemResponseRecord>> garagem() {
        return ResponseEntity.ok(this.garagemService.configGaragem());
    }

    @Operation(summary = "Verificar status da placa", description = "Retorna o status de uma placa específica.")
    @ApiResponse(responseCode = "200", description = "Status da placa retornado com sucesso.")
    @PostMapping("/plate-status")
    public ResponseEntity<StatusPlacaResponseRecord> plateStatus(
        @Parameter(description = "Dados da placa", required = true) @RequestBody PlacaRequestRecord placa) {
        return ResponseEntity.ok(this.garagemService.plateStatus(placa));
    }

    @Operation(summary = "Verificar status da vaga", description = "Retorna o status de uma vaga específica.")
    @ApiResponse(responseCode = "200", description = "Status da vaga retornado com sucesso.")
    @PostMapping("/spot-status")
    public ResponseEntity<StatusVagaResponseRecord> spotStatus(
        @Parameter(description = "Dados da vaga", required = true) @RequestBody StatusVagaRequestRecord vaga) {
        return ResponseEntity.ok(this.garagemService.spotStatus(vaga));
    }

    @Operation(summary = "Consultar receita", description = "Retorna a receita total de um setor em uma data específica.")
    @ApiResponse(responseCode = "200", description = "Receita retornada com sucesso.")
    @GetMapping("/revenue")
    public ResponseEntity<ReceitaResponseRecord> revenue(
        @Parameter(description = "Dados da receita", required = true) @RequestBody ReceitaRequestRecord receita) {
        return ResponseEntity.ok(this.garagemService.receitaGaragem(receita));
    }
}