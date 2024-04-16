package com.example.UniBet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioViewDTO {
    private int id;
    private String nome;
    private String email;
    private String login;
    private double saldo;
    private boolean ehAdmin;
}
