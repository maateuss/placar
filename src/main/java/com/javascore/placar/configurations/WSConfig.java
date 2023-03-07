package com.javascore.placar.configurations;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import com.javascore.placar.handlers.*;

@Configuration
public class WSConfig {

    @Autowired
    private DefaultWebSocketHandler myWebSocketHandler;

    @Autowired
    private GameWebSocketHandler gameWebSocketHandler;
    
    @Bean
    public HandlerMapping handlerMapping(){
        Map<String, WebSocketHandler> handlerMap = Map.of(
                "/stream", myWebSocketHandler,
                "/game/*", gameWebSocketHandler
        );
        return new SimpleUrlHandlerMapping(handlerMap, 1);
    }

}
