package com.example.demo.services;

import com.example.demo.records.EventoGaragemRecord;
import com.example.demo.services.handlers.EventoGaragemHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GaragemService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<String, EventoGaragemHandler> handlers;

    public GaragemService(Map<String, EventoGaragemHandler> handlers) {
        this.handlers = handlers;
    }

    public String eventoGaragem(EventoGaragemRecord evento) {
        log.info(evento.toString());

        return "Evento recebido com sucesso";
    }
}
