package com.example.UniBet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.annotation.Generated;
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
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private LocalDateTime dataJogo;
    private double oddsVitoriaTimeA;
    private double oddsVitoriaTimeB;
    private double oddsEmpate;

    @ManyToOne
    @JoinColumn(name = "id_time_a")
    private Time timeA;

    @ManyToOne
    @JoinColumn(name = "id_time_b")
    private Time timeB;

    private int pontuacaoTimeA;
    private int pontuacaoTimeB;
    private ETipoResultado resultado;
}
