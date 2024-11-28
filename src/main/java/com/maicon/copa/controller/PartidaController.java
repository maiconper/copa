package com.maicon.copa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maicon.copa.model.Equipe;
import com.maicon.copa.model.Partida;
import com.maicon.copa.service.EquipeService;
import com.maicon.copa.service.PartidaService;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private EquipeService equipeService;

    @PostMapping("/gerar")
    public ResponseEntity<List<Partida>> gerarPartidas(@RequestParam String fase) {
        List<Equipe> equipes = equipeService.listarEquipes();
        if (equipes.size() % 2 != 0) {
            return ResponseEntity.badRequest().build(); // Apenas pares de equipes são permitidos
        }
        List<Partida> partidas = partidaService.gerarPartidas(equipes, fase);
        return ResponseEntity.ok(partidas);
    }
    
    @PutMapping("/{id}/resultado")
    public ResponseEntity<Partida> registrarResultado(
            @PathVariable Long id,
            @RequestParam Integer placarEquipe1,
            @RequestParam Integer placarEquipe2) {

        Partida partida = partidaService.registrarResultado(id, placarEquipe1, placarEquipe2);
        return ResponseEntity.ok(partida);
    }
    
    @GetMapping
    public ResponseEntity<List<Partida>> listarPartidasPorFase(@RequestParam String fase) {
        List<Partida> partidas = partidaService.listarPartidasPorFase(fase);
        return ResponseEntity.ok(partidas);
    }
}
