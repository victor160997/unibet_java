package com.example.UniBet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UniBet.model.Time;

public interface TimeDAO extends JpaRepository<Time, Integer> {
    Time findByNome(String nome);
}
