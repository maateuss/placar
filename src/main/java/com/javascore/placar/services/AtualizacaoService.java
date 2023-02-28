package com.javascore.placar.services;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class AtualizacaoService {
    private final Sinks.Many<String> atualizacaoSink = Sinks.many().multicast().onBackpressureBuffer();
    
    public void adicionarAtualizacao(String atualizacao){
        atualizacaoSink.tryEmitNext(atualizacao);
    }

    public Flux<String> getAtualizacaoFlux(){
        return atualizacaoSink.asFlux();
    }

}
