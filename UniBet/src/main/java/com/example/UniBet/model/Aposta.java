package com.example.UniBet.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aposta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private double valorAposta;

    @ManyToOne
    @JoinColumn(name = "jogadorId")
    private Usuario jogador;

    @ManyToOne
    @JoinColumn(name = "jogoId")
    private Jogo jogo;

    private ETipoResultado aposta;
}
