package com.example.UniBet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniBet.model.ETipoResultado;
import com.example.UniBet.model.Jogo;
import com.example.UniBet.model.Time;
import com.example.UniBet.model.DTO.JogoInputDTO;
import com.example.UniBet.model.DTO.JogoUpdateDTO;
import com.example.UniBet.model.DTO.JogoViewDTO;
import com.example.UniBet.model.dao.JogoDAO;
import com.example.UniBet.model.dao.TimeDAO;

@Service
public class JogoService {
    @Autowired
    JogoDAO jdao;

    @Autowired
    TimeDAO tDao;

    public void criaJogo(JogoInputDTO jogo) throws Exception {
        Optional<Time> tAExiste = tDao.findById(jogo.getIdTimeA());
        Optional<Time> tBExiste = tDao.findById(jogo.getIdTimeB());

        if (!tAExiste.isPresent() || !tBExiste.isPresent()) {
            throw new Exception("Time não encontrado");
        }

        if (jogo.getOddsVitoriaTimeA() <= 0 || jogo.getOddsVitoriaTimeB() <= 0 || jogo.getOddsEmpate() <= 0) {
            throw new Exception("As odds precisam ser maior que 0");
        }

        Jogo jogoParaCriar = new Jogo(
                0,
                jogo.getDataJogo(),
                jogo.getOddsVitoriaTimeA(),
                jogo.getOddsVitoriaTimeB(),
                jogo.getOddsEmpate(),
                tAExiste.get(),
                tBExiste.get(),
                0,
                0,
                ETipoResultado.AGUARDANDO);

        jdao.save(jogoParaCriar);
    }

    public JogoViewDTO getJogo(Integer id) throws Exception {
        Optional<Jogo> jogo = jdao.findById(id);
        if (!jogo.isPresent()) {
            throw new Exception("Jogo não encontrado");
        }

        JogoViewDTO jg = new JogoViewDTO();
        jg.setDataJogo(jogo.get().getDataJogo());
        jg.setOddsVitoriaTimeA(jogo.get().getOddsVitoriaTimeA());
        jg.setOddsVitoriaTimeB(jogo.get().getOddsVitoriaTimeB());
        jg.setOddsEmpate(jogo.get().getOddsEmpate());
        jg.setTimeA(jogo.get().getTimeA().getNome());
        jg.setTimeB(jogo.get().getTimeB().getNome());
        jg.setPontuacaoTimeA(jogo.get().getPontuacaoTimeA());
        jg.setPontuacaoTimeB(jogo.get().getPontuacaoTimeB());
        jg.setResultado(jogo.get().getResultado());

        return jg;
    }

    public void atualizaJogo(Integer id, JogoUpdateDTO jogo) throws Exception {
        Optional<Jogo> jg = jdao.findById(id);
        if (!jg.isPresent()) {
            throw new Exception("Jogo não encontrado");
        }

        jg.get().setDataJogo(jogo.getDataJogo());
        jg.get().setOddsVitoriaTimeA(jogo.getOddsVitoriaTimeA());
        jg.get().setOddsVitoriaTimeB(jogo.getOddsVitoriaTimeB());
        jg.get().setOddsEmpate(jogo.getOddsEmpate());
        jg.get().setPontuacaoTimeA(jogo.getPontuacaoTimeA());
        jg.get().setPontuacaoTimeB(jogo.getPontuacaoTimeB());
        jg.get().setResultado(jogo.getResultado());

        jdao.save(jg.get());
    }
}
