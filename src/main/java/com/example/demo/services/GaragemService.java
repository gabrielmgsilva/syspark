package com.example.demo.services;

import com.example.demo.records.EventoGaragemRecord;
import com.example.demo.records.StatusEventoRecord;
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

    public StatusEventoRecord eventoGaragem(EventoGaragemRecord evento) {
        EventoGaragemHandler handler = handlers.get(evento.tipoEvento());

        if (handler != null) {
           return handler.handle(evento);
        } else {
            log.warn("Handler não encontrado para o evento: " + evento.tipoEvento());
            throw new IllegalArgumentException("Tipo de evento desconhecido: " + evento.tipoEvento());
        }
    }
}
