package com.javascore.placar.configurations;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import com.javascore.placar.handlers.DefaultWebSocketHandler;

@Configuration
public class WSConfig {

    @Autowired
    private DefaultWebSocketHandler myWebSocketHandler;
    
    @Bean
    public HandlerMapping handlerMapping(){
        Map<String, DefaultWebSocketHandler> handlerMap = Map.of(
                "/stream", myWebSocketHandler
        );
        return new SimpleUrlHandlerMapping(handlerMap, 1);
    }

}
