package com.javascore.placar.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.WebSocketMessage;
import com.javascore.placar.services.JogoService;
import com.javascore.placar.classes.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.core.publisher.Flux;

@Service
public class GameWebSocketHandler implements WebSocketHandler {
    
    @Autowired
    private JogoService jogos;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        var gameId = webSocketSession.getHandshakeInfo().getUri().getPath().split("/")[2];

        Mono<Void> welcome = webSocketSession.send(Mono.just(webSocketSession.textMessage(
            String.format("Listening to game: %s", gameId))));

        Flux<WebSocketMessage> jogosOutput = jogos.getJogosFlux().filter(m -> m.getId().toString().equals(gameId)).map(jogo -> webSocketSession.textMessage(jogo.toString()));

        return welcome.then(webSocketSession.send(jogosOutput)).subscribeOn(Schedulers.parallel());

    }

}
