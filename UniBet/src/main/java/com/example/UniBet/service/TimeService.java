package com.example.UniBet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniBet.model.Time;
import com.example.UniBet.model.dao.TimeDAO;

@Service
public class TimeService {

    @Autowired
    private TimeDAO dTime;

    public Time verificaSalvamento(Time time) throws Exception {
        if (time.getNome().isEmpty() || time.getNome().isBlank()) {
            String errorMessage = "Invalid time name.";
            throw new Exception(errorMessage);
        }

        Time existingTime = dTime.findByNome(time.getNome());

        if (existingTime != null) {
            throw new Exception("Time already exists.");
        }

        return dTime.save(time);
    }

    public Object getAllTimes() {
        return dTime.findAll();
    }

    public void deleteTime(int id) throws Exception {
        Time existinTime = dTime.findById(id).orElse(null);

        if (existinTime == null) {
            throw new Exception("Time not found.");
        }

        dTime.delete(existinTime);
    }

    public Time getTime(int id) throws Exception {
        Time existingTime = dTime.findById(id).orElse(null);

        if (existingTime == null) {
            throw new Exception("Time not found.");
        }

        return existingTime;
    }

    public Time updateTime(int id, Time time) throws Exception {
        Optional<Time> t = dTime.findById(id);
        if (!t.isPresent()) {
            throw new Exception("time " + id + " não existe");
        }
        if (time.getNome().isEmpty() || time.getNome().isBlank()) {
            throw new Exception("nome do time não pode ser vazio");
        }
        Time timeExiste = dTime.findByNome(time.getNome());
        if (timeExiste != null) {
            throw new Exception("time " + timeExiste.getNome() + " já cadastrado");
        }
        Time timeAlterado = t.get();
        timeAlterado.setNome(time.getNome());
        return dTime.save(timeAlterado);
    }
}
