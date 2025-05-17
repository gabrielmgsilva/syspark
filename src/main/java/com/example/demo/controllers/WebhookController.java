package com.example.demo.controllers;

import com.example.demo.records.EventoGaragemRecord;
import com.example.demo.services.GaragemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private GaragemService garagemService;

    public WebhookController(GaragemService garagemService) {
        log.info("WebhookController initialized");
        this.garagemService = garagemService;
    }

    @PostMapping
    public ResponseEntity<?> eventoGaragem(@RequestBody EventoGaragemRecord evento) {
        log.info(evento.toString());
        return ResponseEntity.ok().body(garagemService.eventoGaragem(evento));
    }
}
