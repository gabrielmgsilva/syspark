package com.example.demo.controllers;

import com.example.demo.records.*;
import com.example.demo.services.GaragemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AppController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private GaragemService garagemService;

    public AppController(GaragemService garagemService) {
        log.info("WebhookController initialized");
        this.garagemService = garagemService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<StatusEventoRecord> eventoGaragem(@RequestBody EventoGaragemRecord evento) {
        log.info(evento.toString());
        return ResponseEntity.ok().body(garagemService.eventoGaragem(evento));
    }

    @GetMapping("/garage")
    public ResponseEntity<List<GaragemResponseRecord>> garagem() {
        return ResponseEntity.ok(this.garagemService.configGaragem());
    }

    @PostMapping("/plate-status")
    public ResponseEntity<StatusPlacaResponseRecord> plateStatus(@RequestBody PlacaRequestRecord placa) {
        return ResponseEntity.ok(this.garagemService.plateStatus(placa));
    }

    @PostMapping("spot-status")
    public ResponseEntity<StatusVagaResponseRecord> spotStatus(@RequestBody StatusVagaRequestRecord vaga) {
        return ResponseEntity.ok(this.garagemService.spotStatus(vaga));
    }

    @GetMapping("/revenue")
    public ResponseEntity<ReceitaResponseRecord> revenue(@RequestBody ReceitaRequestRecord receita) {
        return ResponseEntity.ok(this.garagemService.receitaGaragem(receita));
    }
}
