package com.example.UniBet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UniBet.model.DTO.UsuarioCreateDTO;
import com.example.UniBet.model.DTO.UsuarioUpdateDTO;
import com.example.UniBet.model.DTO.UsuarioViewDTO;
import com.example.UniBet.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService sUsuario;

    public UsuarioController(UsuarioService sUsuario) {
        this.sUsuario = sUsuario;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUsuario(@RequestBody(required = true) UsuarioCreateDTO usuario) {
        try {
            this.sUsuario.verificaSalvamento(usuario);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(this.sUsuario.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable(required = true) int id) {
        try {
            UsuarioViewDTO usuario = this.sUsuario.getUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(required = true) int id) {
        try {
            this.sUsuario.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable(required = true) int id,
            @RequestBody UsuarioUpdateDTO usuario) {
        try {
            sUsuario.updateUser(id, usuario);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
