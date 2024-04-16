package com.example.UniBet.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.UniBet.model.Aposta;
import com.example.UniBet.model.ETipoResultado;

public interface ApostaDAO extends JpaRepository<Aposta, Integer> {
    public List<Aposta> findByJogadorIdAndJogoResultado(int id, ETipoResultado tipo);

    public Integer countByJogadorId(Integer id);

    @Query("select count(a) from Aposta a where a.jogador.id = :id ")
    public Integer quantidadeApostasJogador(Integer id);
}