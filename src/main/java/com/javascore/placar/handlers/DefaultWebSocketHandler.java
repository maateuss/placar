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

        Flux<WebSocketMessage> output = atualizacoes.getAtualizacaoFlux().map(value -> webSocketSession.textMessage(value));    

        return webSocketSession.send(output);    
    }

}
