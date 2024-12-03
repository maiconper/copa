package com.maicon.copa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maicon.copa.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
	
	@Query("SELECT p FROM Partida p WHERE p.fase = :fase")
	List<Partida> findByFase(@Param("fase") String fase);
	
	@Query("SELECT p.equipe2.id FROM Partida p WHERE p.placarEquipe1 > p.placarEquipe2 " +
		       "UNION SELECT p.equipe1.id FROM Partida p WHERE p.placarEquipe2 > p.placarEquipe1")
		List<Long> findEquipesEliminadas();
	
	@Query("SELECT p FROM Partida p WHERE p.equipe1.id = :equipeId OR p.equipe2.id = :equipeId")
	List<Partida> findPartidasPorEquipe(@Param("equipeId") Long equipeId);

}