package com.example.UniBet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UniBet.model.Aposta;
import com.example.UniBet.model.DTO.ApostaInputDTO;
import com.example.UniBet.model.DTO.ApostaViewDTO;
import com.example.UniBet.service.ApostaService;

@RestController
@RequestMapping("/aposta")
public class ApostaController {

    @Autowired
    ApostaService apoServ;

    @PostMapping()
    public ResponseEntity<?> criaAposta(@RequestBody ApostaInputDTO aposta) {
        try {
            apoServ.criaAposta(aposta);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornaAposta(@PathVariable Integer id) {
        try {
            ApostaViewDTO a = apoServ.getAposta(id);
            return ResponseEntity.ok(a);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getApostasUSuario(@PathVariable Integer id) {

        List<ApostaViewDTO> lista = apoServ.getApostaUsuario(id);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{id}/count")
    public ResponseEntity<?> getApostasUsuarioCount(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(apoServ.getCountApostaUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
