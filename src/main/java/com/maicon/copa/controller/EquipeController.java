package com.maicon.copa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maicon.copa.model.Equipe;
import com.maicon.copa.service.EquipeService;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping
    public ResponseEntity<Equipe> cadastrarEquipe(@RequestBody Equipe equipe) {
        Equipe novaEquipe = equipeService.cadastrarEquipe(equipe);
        return ResponseEntity.ok(novaEquipe);
    }
    
    @GetMapping
    public ResponseEntity<List<Equipe>> listarEquipes() {
        List<Equipe> equipes = equipeService.listarEquipes();
        return ResponseEntity.ok(equipes);
    }
}
