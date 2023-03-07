package com.javascore.placar.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.javascore.placar.services.JogoService;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.*;

@Service
public class DefaultWebSocketHandler implements WebSocketHandler {


    @Autowired
    private JogoService jogos;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        Mono<Long> jogosDisponiveis = jogos.getJogos().count().map(count -> {
            if (count != null) {
                return count;
            } else {
                throw new RuntimeException("Erro ao obter a quantidade de jogos disponíveis");
            }
        });
    
        Mono<Void> welcome = webSocketSession.send(Mono.just(webSocketSession.textMessage(
            "Seja bem vindo ao Java Score!!")));

        Flux<WebSocketMessage> jogosOutput = jogos.getJogosFlux().map(jogo -> webSocketSession.textMessage(jogo.toString()));

        return welcome.then(webSocketSession.send(jogosDisponiveis.map(count ->
                webSocketSession.textMessage(String.format("Jogos disponíveis: %d", count))))
            .then(webSocketSession.send(jogosOutput))).subscribeOn(Schedulers.parallel());
    }

}
