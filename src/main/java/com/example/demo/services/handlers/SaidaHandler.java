package com.example.demo.services.handlers;

import com.example.demo.domain.enums.SituacaoVaga;
import com.example.demo.domain.models.EventoGaragem;
import com.example.demo.domain.models.Receita;
import com.example.demo.domain.models.Vaga;
import com.example.demo.domain.repositories.EventoGaragemRepository;
import com.example.demo.domain.repositories.ReceitaRepository;
import com.example.demo.domain.repositories.VagaRepository;
import com.example.demo.records.EventoGaragemRecord;
import com.example.demo.records.StatusEventoRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("EXIT")
public class SaidaHandler implements EventoGaragemHandler{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventoGaragemRepository eventoGaragemRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Override
    public StatusEventoRecord handle(EventoGaragemRecord evento) {
        log.info("{}", evento);

        if (evento.placa() == null || evento.placa().isEmpty()) {
            log.error("Placa não informada");
            throw new IllegalArgumentException("Placa não informada");
        }

        EventoGaragem eventoEntrada = this.eventoGaragemRepository.buscarUltimoEventoPorPlaca(evento.placa());

        if (null == eventoEntrada) throw new IllegalArgumentException("Veículo não encontrado no estacionamento!");

        EventoGaragem eventoSaida = this.eventoGaragemRepository.save(new EventoGaragem(evento.placa(), evento.tipoEvento()));

        if (null != eventoEntrada.getVaga()) {
            Vaga vaga = vagaRepository.findById(eventoEntrada.getVaga().getId()).orElse(null);
            assert vaga != null;
            vaga.setSituacao(SituacaoVaga.LIVRE);
            this.vagaRepository.save(vaga);

            eventoSaida.setVaga(vaga);
        }

        this.eventoGaragemRepository.save(eventoSaida);

        calcularSaida(eventoSaida, eventoEntrada);

        return StatusEventoRecord.build(eventoSaida.getId(), evento.placa(), eventoSaida.getDtCriacao(), evento.tipoEvento());
    }

    private void calcularSaida(EventoGaragem eventoSaida, EventoGaragem eventoEntrada) {
        double valorBase = eventoEntrada.getVaga().getGaragem().getPrecoBase().doubleValue();

        long tempoPermanencia = java.time.Duration.between(eventoEntrada.getDtCriacao(), eventoSaida.getDtCriacao()).toHours();

        if (tempoPermanencia <= 0) {
            throw new IllegalArgumentException("Tempo de permanência inválido!");
        }

        double custo = valorBase * eventoEntrada.getTaxa() * tempoPermanencia;

        Receita receita = new Receita();
        receita.setValor(BigDecimal.valueOf(custo));
        receita.setGaragem(eventoEntrada.getVaga().getGaragem());
        receitaRepository.save(receita);

        log.info("Custo calculado para a saída: R$ {}", custo);
    }
}
