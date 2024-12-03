package com.maicon.copa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maicon.copa.model.Equipe;
import com.maicon.copa.repository.EquipeRepository;
import com.maicon.copa.repository.PartidaRepository;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;
    
    @Autowired
    private PartidaRepository partidaRepository;

    public Equipe cadastrarEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }
   
    public List<Equipe> listarEquipes() {
        return equipeRepository.findAll();
    }
    
    public List<Equipe> listarEquipesRestantes() {
        List<Equipe> equipes = equipeRepository.findAll();
        List<Long> equipesEliminadas = partidaRepository.findEquipesEliminadas();
        return equipes.stream()
                .filter(equipe -> !equipesEliminadas.contains(equipe.getId()))
                .toList();
    }
}
