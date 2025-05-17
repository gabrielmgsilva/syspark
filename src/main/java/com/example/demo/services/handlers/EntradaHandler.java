package com.example.demo.services.handlers;

import com.example.demo.records.EventoGaragemRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EntradaHandler implements EventoGaragemHandler{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(EventoGaragemRecord eventoGaragem) {
        log.info("EntradaHandler: " + eventoGaragem.toString());
    }
}
