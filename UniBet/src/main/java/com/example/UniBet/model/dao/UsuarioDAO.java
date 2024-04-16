package com.example.UniBet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UniBet.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);

    Usuario findByLogin(String login);
}
