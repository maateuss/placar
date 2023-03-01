package com.javascore.placar.services;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.javascore.placar.classes.Jogo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class JogoService {
    private final Sinks.Many<Jogo> jogosSink = Sinks.many().multicast().onBackpressureBuffer();
    private final Map<UUID, Jogo> jogosMap = new ConcurrentHashMap<>();

    
    public void adicionarJogo(Jogo novoJogo){
        jogosMap.put(novoJogo.getId(), novoJogo);
        jogosSink.tryEmitNext(novoJogo);
    }

    public Flux<Jogo> getJogosFlux(){
        return jogosSink.asFlux();
    }

    public Mono<Jogo> getJogo(UUID id) {
        return Mono.justOrEmpty(jogosMap.get(id));
    }

    public Flux<Jogo> getJogos() {
        return Flux.fromIterable(jogosMap.values());
    }

    public Mono<Jogo> atualizarJogo(UUID id, Jogo jogoAtualizado) {
        return Mono.fromCallable(() -> {
            Jogo jogoExistente = jogosMap.get(id);
            if (jogoExistente == null) {
                throw new Exception("Jogo não encontrado");
            }
            jogoAtualizado.setId(jogoExistente.getId());
            jogosMap.put(jogoAtualizado.getId(), jogoAtualizado);
            jogosSink.tryEmitNext(jogoAtualizado);
            return jogoAtualizado;
        });
    }

    public Mono<Boolean> removerJogo(UUID id) {
        return Mono.fromCallable(() -> {
            Jogo jogoExistente = jogosMap.get(id);
            if (jogoExistente == null) {
                throw new Exception("Jogo não encontrado");
            }
            jogosMap.remove(id);
            jogosSink.tryEmitNext(jogoExistente.removerIdentificador());
            return true;
        });
    }
}
