package com.maicon.copa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maicon.copa.model.Equipe;
import com.maicon.copa.repository.EquipeRepository;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    public Equipe cadastrarEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }
}
