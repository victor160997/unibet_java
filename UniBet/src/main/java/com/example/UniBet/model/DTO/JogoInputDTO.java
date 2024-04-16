package com.example.UniBet.model.DTO;

import java.time.LocalDateTime;

import com.example.UniBet.model.ETipoResultado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoInputDTO {
    private LocalDateTime dataJogo;
    private double oddsVitoriaTimeA;
    private double oddsVitoriaTimeB;
    private double oddsEmpate;
    private int idTimeA;
    private int idTimeB;
}
