package com.example.UniBet.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/desenv")
public class DesenvolvimentoController {
    static ArrayList<String> desenvolvedores;
    static {
        desenvolvedores = new ArrayList<>();
        desenvolvedores.add("Zezin da Silva");
        desenvolvedores.add("PeDrin Morreira");
        desenvolvedores.add("Gustin Marmelo");
        desenvolvedores.add("Victor Um");
        desenvolvedores.add("Victor Dois");
    }

    @GetMapping("/time")
    public String getDesenvTime() {
        String nome = "";
        for (String n : desenvolvedores) {
            nome += n + "; ";
        }
        return nome;
    }

    @GetMapping("/time/{posicao}")
    public String getDesenvTimeOne(@PathVariable int posicao) {
        if (posicao >= 1 && posicao <= desenvolvedores.size()) {
            return desenvolvedores.get(posicao - 1);
        } else {
            return "indice incorreto";
        }
    }

    @PostMapping("/time")
    public String saveDesenvolvedores(@RequestBody String nome) {
        if (nome != null && !nome.isBlank() && !nome.isEmpty()) {
            desenvolvedores.add(nome);
            return "Salvo com sucesso";
        }
        return "Erro ao Salvar";
    }

    @DeleteMapping("/time/{idx}")
    public String deleteDesenvolvedores(@PathVariable int idx) {
        if (idx >= 1 && idx <= desenvolvedores.size()) {
            desenvolvedores.remove(idx - 1);
            return "Removido com sucesso";
        } else {
            return "indice incorreto";
        }
    }

    @PutMapping("/time/{idx}")
    public String updateDesenvolvedores(@PathVariable int idx, @RequestBody String nome) {
        if (idx >= 1 && idx <= desenvolvedores.size()) {
            desenvolvedores.set(idx - 1, nome);
            return "Atualizado com sucesso";
        } else {
            return "indice incorreto";
        }
    }

    @GetMapping("/time/search")
    public ArrayList<String> buscarDesenvolvedores(@RequestParam(required = true) String nome,
            @RequestHeader(required = true) String token) {

        ArrayList<String> resultado = new ArrayList<>();

        if (!token.equals("123")) {
            resultado.add("Token inválido");
            return resultado;
        }

        for (String n : desenvolvedores) {
            if (n.toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(n);
            }
        }
        return resultado;
    }
}