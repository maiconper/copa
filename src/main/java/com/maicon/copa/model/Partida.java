package com.maicon.copa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_equipe1", nullable = false)
    private Equipe equipe1;

    @ManyToOne
    @JoinColumn(name = "id_equipe2", nullable = false)
    private Equipe equipe2;

    private LocalDateTime dataHora;

    private String fase;

    private Integer placarEquipe1;

    private Integer placarEquipe2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Equipe getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}

	public Equipe getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public Integer getPlacarEquipe1() {
		return placarEquipe1;
	}

	public void setPlacarEquipe1(Integer placarEquipe1) {
		this.placarEquipe1 = placarEquipe1;
	}

	public Integer getPlacarEquipe2() {
		return placarEquipe2;
	}

	public void setPlacarEquipe2(Integer placarEquipe2) {
		this.placarEquipe2 = placarEquipe2;
	}
   
    

    // Getters e Setters
}