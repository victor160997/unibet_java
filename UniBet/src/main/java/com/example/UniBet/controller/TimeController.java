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

import com.example.UniBet.model.Time;
import com.example.UniBet.service.TimeService;

@RestController
@RequestMapping("/time")
public class TimeController {

    private TimeService sTime;

    public TimeController(TimeService sTime) {
        this.sTime = sTime;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveTime(@RequestBody(required = true) Time time) {
        try {
            Time savedTime = this.sTime.verificaSalvamento(time);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTime);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTimes() {
        return ResponseEntity.status(HttpStatus.OK).body(this.sTime.getAllTimes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTime(@PathVariable(required = true) int id) {
        try {
            Time time = this.sTime.getTime(id);
            return ResponseEntity.status(HttpStatus.OK).body(time);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTime(@PathVariable(required = true) int id) {
        try {
            this.sTime.deleteTime(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTime(@PathVariable(required = true) int id,
            @RequestBody Time time) {
        try {
            Time t = sTime.updateTime(id, time);
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
