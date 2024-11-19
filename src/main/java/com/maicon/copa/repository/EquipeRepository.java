package com.maicon.copa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maicon.copa.model.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}