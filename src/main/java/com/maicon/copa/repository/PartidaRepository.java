package com.maicon.copa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maicon.copa.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
	
	@Query("SELECT p FROM Partida p WHERE p.fase = :fase")
	List<Partida> findByFase(@Param("fase") String fase);
}