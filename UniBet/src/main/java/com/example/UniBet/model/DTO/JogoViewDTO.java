package com.example.UniBet.model.DTO;

import java.time.LocalDateTime;

import com.example.UniBet.model.ETipoResultado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoViewDTO {
    private LocalDateTime dataJogo;
    private double oddsVitoriaTimeA;
    private double oddsVitoriaTimeB;
    private double oddsEmpate;
    private String timeA;
    private String timeB;
    private int pontuacaoTimeA;
    private int pontuacaoTimeB;
    private ETipoResultado resultado;
}
