package com.javascore.placar.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.javascore.placar.services.AtualizacaoService;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class DefaultWebSocketHandler implements WebSocketHandler {

    @Autowired
    private AtualizacaoService atualizacoes;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        Mono<Void> welcome = webSocketSession.send(Mono.just(webSocketSession.textMessage("Seja bem vindo ao Java Score!! jogos disponiveis: 0 :D")));

        Flux<WebSocketMessage> output = atualizacoes.getAtualizacaoFlux().map(value -> webSocketSession.textMessage(value));    

        return welcome.then(webSocketSession.send(output));    
    }

}
