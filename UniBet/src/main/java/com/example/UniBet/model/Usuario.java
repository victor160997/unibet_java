package com.example.UniBet.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String login;
    private double saldo;
    private boolean ehAdmin;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL)
    private List<Aposta> minhasApostas;

    public void sacar(double valorAposta) {
        if (valorAposta > 0 && valorAposta <= saldo) {
            saldo -= valorAposta;
        }
    }
}
