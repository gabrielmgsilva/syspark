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

@Component("EXIT")
public class SaidaHandler implements EventoGaragemHandler{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventoGaragemRepository eventoGaragemRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Override
    public StatusEventoRecord handle(EventoGaragemRecord evento) {
        log.info("{}", evento);

        if (evento.placa() == null || evento.placa().isEmpty()) {
            log.error("Placa não informada");
            throw new IllegalArgumentException("Placa não informada");
        }

        EventoGaragem eventoGaragem = this.eventoGaragemRepository.buscarUltimoEventoPorPlaca(evento.placa());

        if (null == eventoGaragem) throw new IllegalArgumentException("Veículo não encontrado no estacionamento!");

        EventoGaragem eventoSaida = this.eventoGaragemRepository.save(new EventoGaragem(evento.placa(), evento.tipoEvento()));

        if (null != eventoGaragem.getVaga()) {
            Vaga vaga = vagaRepository.findById(eventoGaragem.getVaga().getId()).orElse(null);
            assert vaga != null;
            vaga.setSituacao(SituacaoVaga.LIVRE);
            this.vagaRepository.save(vaga);

            eventoSaida.setVaga(vaga);
        }

        this.eventoGaragemRepository.save(eventoGaragem);

        return StatusEventoRecord.build(eventoSaida.getId(), evento.placa(), eventoSaida.getDtCriacao(), evento.tipoEvento());
    }
}
