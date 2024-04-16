package com.example.UniBet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoViewDTO {

    private String nome;
    private double valorGanho;
}
