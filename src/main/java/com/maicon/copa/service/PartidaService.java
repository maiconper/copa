package com.maicon.copa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maicon.copa.model.Equipe;
import com.maicon.copa.model.Partida;
import com.maicon.copa.repository.PartidaRepository;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    public List<Partida> gerarPartidas(List<Equipe> equipes, String fase) {
        List<Partida> partidas = new ArrayList<>();
        for (int i = 0; i < equipes.size(); i += 2) {
            Partida partida = new Partida();
            partida.setEquipe1(equipes.get(i));
            partida.setEquipe2(equipes.get(i + 1));
            partida.setDataHora(LocalDateTime.now().plusDays(i / 2)); // Exemplo: datas diferentes
            partida.setFase(fase);
            partidas.add(partida);
        }
        return partidaRepository.saveAll(partidas);
    }
    
    public Partida registrarResultado(Long id, Integer placarEquipe1, Integer placarEquipe2) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));

        partida.setPlacarEquipe1(placarEquipe1);
        partida.setPlacarEquipe2(placarEquipe2);
        return partidaRepository.save(partida);
    }
    
    public List<Partida> listarPartidasPorFase(String fase) {
        return partidaRepository.findByFase(fase);
    }
    
}
