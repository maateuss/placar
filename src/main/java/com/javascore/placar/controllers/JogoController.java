package com.javascore.placar.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javascore.placar.classes.Jogo;
import com.javascore.placar.services.JogoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @PostMapping
    public Mono<Jogo> adicionarJogo(@RequestBody Jogo novoJogo) {
        jogoService.adicionarJogo(novoJogo);
        return Mono.just(novoJogo);
    }

    @GetMapping
    public Flux<Jogo> listarJogos() {
        return jogoService.getJogos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Jogo>> buscarJogoPorId(@PathVariable UUID id) {
        return jogoService.getJogo(id)
                .map(jogo -> ResponseEntity.ok(jogo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Jogo>> atualizarJogo(@PathVariable UUID id, @RequestBody Jogo jogoAtualizado) {
        return jogoService.atualizarJogo(id, jogoAtualizado)
                .map(jogo -> ResponseEntity.ok(jogo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> removerJogo(@PathVariable UUID id) {
        return jogoService.removerJogo(id)
                .map(deleted -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}