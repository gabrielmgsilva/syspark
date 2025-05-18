package com.example.demo.controllers;

import com.example.demo.records.EventoGaragemRecord;
import com.example.demo.records.StatusEventoRecord;
import com.example.demo.services.GaragemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> garage() {
        //TODO: Implement garage logic
        return ResponseEntity.ok("Garage is open");
    }

    @PostMapping("/plate-status")
    public ResponseEntity<String> plateStatus() {
        //TODO: Implement plate status logic
        return ResponseEntity.ok("Plate status is OK");
    }

    @PostMapping("spot-status")
    public ResponseEntity<String> spotStatus() {
        //TODO: Implement spot status logic
        return ResponseEntity.ok("Spot status is OK");
    }

    @GetMapping("/revenue")
    public ResponseEntity<String> revenue() {
        //TODO: Implement revenue logic
        return ResponseEntity.ok("Revenue is OK");
    }
}
