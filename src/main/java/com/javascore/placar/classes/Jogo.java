package com.javascore.placar.classes;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String timeCasa;
    private String timeVisitante;
    private int golsTimeCasa;
    private int golsTimeVisitante;
    private List<String> eventos;

    public Jogo removerIdentificador() {
        this.id = null;
        return this;
    }

    @Override
    public String toString() {
        return "{"
            + "\"id\":\"" + id + "\","
            + "\"timeCasa\":\"" + timeCasa + "\","
            + "\"timeVisitante\":\"" + timeVisitante + "\","
            + "\"golsTimeCasa\":\"" + golsTimeCasa + "\","
            + "\"golsTimeVisitante\":\"" + golsTimeVisitante + "\","
            + "\"eventos\":" + eventos.toString()
            + "}";
    }
}
