package com.javascore.placar.configurations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import com.javascore.placar.handlers.DefaultWebSocketHandler;

@Configuration
public class WSConfig {

    @Autowired
    private DefaultWebSocketHandler myWebSocketHandler;
    
    @Bean
    public HandlerMapping handlerMapping(){
        Map<String, DefaultWebSocketHandler> handlerMap = Map.of(
                "/test", myWebSocketHandler
        );
        return new SimpleUrlHandlerMapping(handlerMap, 1);
    }

}
