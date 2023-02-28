package com.javascore.placar.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javascore.placar.services.AtualizacaoService;

@RestController
@RequestMapping("/update")
public class PlacarController {

    @Autowired
    private AtualizacaoService service;
    
    @PostMapping
    public ResponseEntity<String> informUpdate(@RequestBody String atualizacao) {
        service.adicionarAtualizacao(atualizacao);
        return ResponseEntity.ok("Updated!");
    }

}
