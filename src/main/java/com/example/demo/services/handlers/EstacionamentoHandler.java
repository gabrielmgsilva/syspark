package com.example.demo.services.handlers;

import com.example.demo.domain.enums.SituacaoVaga;
import com.example.demo.domain.models.EventoGaragem;
import com.example.demo.domain.models.Vaga;
import com.example.demo.domain.repositories.EventoGaragemRepository;
import com.example.demo.domain.repositories.VagaRepository;
import com.example.demo.records.EventoGaragemRecord;
import com.example.demo.records.StatusEventoRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("PARKED")
public class EstacionamentoHandler implements EventoGaragemHandler{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private EventoGaragemRepository eventoGaragemRepository;

    @Override
    public StatusEventoRecord handle(EventoGaragemRecord evento) {
        log.info("{}", evento);

        Vaga vaga = this.vagaRepository.findByLatitudeAndLongitude(evento.latitude(), evento.longitude())
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada ou inexistente"));

        if (vaga.getSituacao().equals(SituacaoVaga.OCUPADA)) throw new IllegalArgumentException("Vaga já ocupada");

        EventoGaragem eventoGaragem = new EventoGaragem(evento.placa(), evento.tipoEvento(), vaga);

        vaga.setSituacao(SituacaoVaga.OCUPADA);

        this.vagaRepository.save(vaga);
        this.eventoGaragemRepository.save(eventoGaragem);

        return StatusEventoRecord.build(eventoGaragem.getId(), evento.placa(), eventoGaragem.getDtCriacao(), evento.tipoEvento());
    }
}
