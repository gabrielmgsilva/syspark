package com.example.demo.services.handlers;

import com.example.demo.domain.models.EventoGaragem;
import com.example.demo.domain.repositories.EventoGaragemRepository;
import com.example.demo.records.EventoGaragemRecord;
import com.example.demo.records.StatusEventoRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ENTRY")
public class EntradaHandler implements EventoGaragemHandler{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventoGaragemRepository eventoGaragemRepository;

    @Override
    public StatusEventoRecord handle(EventoGaragemRecord evento) {
        EventoGaragem eventoGaragem = new EventoGaragem(evento.placa(), evento.tipoEvento());

        eventoGaragem = eventoGaragemRepository.save(eventoGaragem);

        return StatusEventoRecord.build(eventoGaragem.getId(), eventoGaragem.getPlaca(), eventoGaragem.getDtCriacao(), eventoGaragem.getTipoEvento());
    }
}
