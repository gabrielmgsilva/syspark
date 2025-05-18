package com.example.demo.services.handlers;

import com.example.demo.records.EventoGaragemRecord;
import com.example.demo.records.StatusEventoRecord;

public interface EventoGaragemHandler {

    StatusEventoRecord handle(EventoGaragemRecord eventoGaragem);
}
