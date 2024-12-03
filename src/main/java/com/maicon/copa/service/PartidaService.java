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

        if (placarEquipe1.equals(placarEquipe2)) {
            throw new RuntimeException("Empates não são permitidos em eliminatórias.");
        }

        partida.setPlacarEquipe1(placarEquipe1);
        partida.setPlacarEquipe2(placarEquipe2);
        return partidaRepository.save(partida);
    }
    
    public List<Partida> listarPartidasPorFase(String fase) {
        return partidaRepository.findByFase(fase);
    }
    
    public List<Partida> avancarParaProximaFase(String faseAtual) {
        List<Partida> partidasDaFase = partidaRepository.findByFase(faseAtual);

        // Verificar se todas as partidas têm resultados registrados
        boolean todasComResultados = partidasDaFase.stream().allMatch(
                partida -> partida.getPlacarEquipe1() != null && partida.getPlacarEquipe2() != null
        );

        if (!todasComResultados) {
            throw new RuntimeException("Nem todas as partidas da fase atual têm resultados registrados.");
        }

        // Determinar vencedores
        List<Equipe> vencedores = new ArrayList<>();
        for (Partida partida : partidasDaFase) {
            if (partida.getPlacarEquipe1() > partida.getPlacarEquipe2()) {
                vencedores.add(partida.getEquipe1());
            } else {
                vencedores.add(partida.getEquipe2());
            }
        }

        // Gerar partidas para a próxima fase
        String proximaFase = determinarProximaFase(faseAtual);
        return gerarPartidas(vencedores, proximaFase);
    }
    
    private String determinarProximaFase(String faseAtual) {
        switch (faseAtual.toLowerCase()) {
            case "oitavas":
                return "quartas";
            case "quartas":
                return "semifinal";
            case "semifinal":
                return "final";
            default:
                throw new RuntimeException("Fase atual inválida para avanço.");
        }
    }
    
    public List<Partida> listarPartidasPorEquipe(Long equipeId) {
        return partidaRepository.findPartidasPorEquipe(equipeId);
    }
    
}
