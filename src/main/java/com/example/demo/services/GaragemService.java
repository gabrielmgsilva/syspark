package com.example.demo.services;

import com.example.demo.domain.enums.SituacaoVaga;
import com.example.demo.domain.models.EventoGaragem;
import com.example.demo.domain.models.Garagem;
import com.example.demo.domain.models.Vaga;
import com.example.demo.domain.repositories.EventoGaragemRepository;
import com.example.demo.domain.repositories.GaragemRepository;
import com.example.demo.domain.repositories.ReceitaRepository;
import com.example.demo.domain.repositories.VagaRepository;
import com.example.demo.records.*;
import com.example.demo.services.handlers.EventoGaragemHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GaragemService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<String, EventoGaragemHandler> handlers;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private EventoGaragemRepository eventoGaragemRepository;

    @Autowired
    private GaragemRepository garagemRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

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

    public StatusVagaResponseRecord spotStatus(StatusVagaRequestRecord vaga){
        Vaga infoVaga = this.vagaRepository.findByLatitudeAndLongitude(vaga.latitude(), vaga.longitude())
            .orElseThrow(() -> new RuntimeException("Vaga não encontrada ou inexistente"));

        Optional<EventoGaragem> eventoGaragemOptional = this.eventoGaragemRepository.findLastParkedEventByVagaId(infoVaga.getId());

        if (eventoGaragemOptional.isPresent()) {
            EventoGaragem eventoGaragem = eventoGaragemOptional.get();
            return new StatusVagaResponseRecord(infoVaga.getSituacao() == SituacaoVaga.OCUPADA, eventoGaragem.getDtCriacao(), eventoGaragem.getDtCriacao());
        }

        return new StatusVagaResponseRecord(infoVaga.getSituacao() == SituacaoVaga.OCUPADA, null, null);
    }

    public StatusPlacaResponseRecord plateStatus(PlacaRequestRecord placa) {
        EventoGaragem eventoGaragem = this.eventoGaragemRepository.findFirstByPlacaOrderByDtCriacaoDesc(placa.placa())
            .orElseThrow(() -> new RuntimeException("Carro não encontrado ou não está estacionado"));

        if (!eventoGaragem.getTipoEvento().equals("PARKED")) {
            throw new RuntimeException("Carro não está estacionado");
        }

        LocalDateTime dtEntrada = eventoGaragem.getDtCriacao();
        LocalDateTime agora = LocalDateTime.now();

        Vaga vaga = eventoGaragem.getVaga();
        BigDecimal valorBase = vaga.getGaragem().getPrecoBase();
        double taxa = eventoGaragem.getTaxa();

        long horasEstacionado = Duration.between(dtEntrada, agora).toHours();
        BigDecimal preco = valorBase.multiply(BigDecimal.valueOf(taxa)).multiply(BigDecimal.valueOf(horasEstacionado));

        return new StatusPlacaResponseRecord(
            eventoGaragem.getPlaca(),
            preco,
            dtEntrada,
            dtEntrada,
            vaga.getLatitude(),
            vaga.getLongitude()
        );
    }

    public List<GaragemResponseRecord> configGaragem() {
        List<Garagem> garagens = garagemRepository.findAll();

        return garagens.stream().map(garagem -> {
            List<SpotResponseRecord> spots = vagaRepository.findByGaragemId(garagem.getId()).stream()
                .map(vaga -> new SpotResponseRecord(
                    vaga.getId(),
                    garagem.getSetor(),
                    vaga.getLatitude(),
                    vaga.getLongitude(),
                    vaga.getSituacao() == SituacaoVaga.OCUPADA // Retorna true se ocupada, false caso contrário
                ))
                .toList();

            return new GaragemResponseRecord(
                garagem.getSetor(),
                garagem.getPrecoBase(),
                garagem.getCapacidade(),
                garagem.getHoraAbertura(),
                garagem.getHoraFechamento(),
                garagem.getDuracaoLimite(),
                spots
            );
        }).toList();
    }

    public ReceitaResponseRecord receitaGaragem(ReceitaRequestRecord receita) {
        BigDecimal amount = this.receitaRepository.findTotalAmountBySectorAndDate(receita.setor(), receita.data());
        return new ReceitaResponseRecord(amount, "BRL", receita.data().atStartOfDay());
    }
}
