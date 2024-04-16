package com.example.UniBet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniBet.model.Aposta;
import com.example.UniBet.model.ETipoResultado;
import com.example.UniBet.model.Jogo;
import com.example.UniBet.model.Usuario;
import com.example.UniBet.model.DTO.ApostaInputDTO;
import com.example.UniBet.model.DTO.ApostaViewDTO;
import com.example.UniBet.model.dao.ApostaDAO;
import com.example.UniBet.model.dao.JogoDAO;
import com.example.UniBet.model.dao.UsuarioDAO;

@Service
public class ApostaService {

    @Autowired
    UsuarioDAO udao;
    @Autowired
    JogoDAO jdao;
    @Autowired
    ApostaDAO adao;

    public void criaAposta(ApostaInputDTO aposta) throws Exception {

        // Verificar se o apostador existe
        Optional<Usuario> uExiste = udao.findById(aposta.getIdApostador());
        if (!uExiste.isPresent()) {
            throw new Exception("Usuário não encontrado!!!");
        }
        if (uExiste.get().getSaldo() < aposta.getValorAposta()) {
            throw new Exception("Saldo insuficiente!!!");
        }
        Optional<Jogo> jExiste = jdao.findById(aposta.getIdJogo());
        if (!jExiste.isPresent()) {
            throw new Exception("Jogo não encontrado!!!");
        }
        if (jExiste.get().getDataJogo().isBefore(LocalDateTime.now().minusMinutes(30))) {
            throw new Exception("Apostas Encerradas!!!");
        }

        Aposta apo = new Aposta(0, aposta.getValorAposta(),
                uExiste.get(), jExiste.get(), aposta.getResultado());
        adao.save(apo);
        uExiste.get().sacar(aposta.getValorAposta());
        udao.save(uExiste.get());

    }

    public ApostaViewDTO getAposta(Integer id) throws Exception {

        Optional<Aposta> aposta = adao.findById(id);
        if (!aposta.isPresent()) {
            throw new Exception("Aposta " + id + " não encontrada");
        }
        ApostaViewDTO ap = new ApostaViewDTO();
        ap.setId(aposta.get().getId());
        ap.setDataJogo(aposta.get().getJogo().getDataJogo());
        ap.setValorAposta(aposta.get().getValorAposta());
        ap.setIdJogador(aposta.get().getJogador().getId());
        ap.setIdTime1(aposta.get().getJogo().getTimeA().getId());
        ap.setIdTime2(aposta.get().getJogo().getTimeB().getId());
        ap.setNomeJogador(aposta.get().getJogador().getNome());
        ap.setTime1(aposta.get().getJogo().getTimeA().getNome());
        ap.setTime2(aposta.get().getJogo().getTimeB().getNome());
        ap.setResultadoApostado(aposta.get().getAposta());
        ap.setResultadoJogo(aposta.get().getJogo().getResultado());
        ap.setAcertou(aposta.get().getAposta() == aposta.get().getJogo().getResultado());

        return ap;
    }

    public List<ApostaViewDTO> getApostaUsuario(Integer id) {

        List<Aposta> lista = adao.findByJogadorIdAndJogoResultado(id, ETipoResultado.AGUARDANDO);
        List<ApostaViewDTO> listaDTO = new ArrayList<>();
        for (Aposta a : lista) {
            ApostaViewDTO ap = new ApostaViewDTO();
            ap.setId(a.getId());
            ap.setDataJogo(a.getJogo().getDataJogo());
            ap.setValorAposta(a.getValorAposta());
            ap.setIdJogador(a.getJogador().getId());
            ap.setIdTime1(a.getJogo().getTimeA().getId());
            ap.setIdTime2(a.getJogo().getTimeB().getId());
            ap.setNomeJogador(a.getJogador().getNome());
            ap.setTime1(a.getJogo().getTimeA().getNome());
            ap.setTime2(a.getJogo().getTimeB().getNome());
            ap.setResultadoApostado(a.getAposta());
            ap.setResultadoJogo(a.getJogo().getResultado());
            ap.setAcertou(a.getAposta() == a.getJogo().getResultado());

            listaDTO.add(ap);
        }

        return listaDTO;

    }

    public Integer getCountApostaUsuario(Integer id) throws Exception {
        Optional<Usuario> uExiste = udao.findById(id);
        if (!uExiste.isPresent()) {
            throw new Exception("Usuário não encontrado!!!");
        }
        return adao.countByJogadorId(id);
    }
}