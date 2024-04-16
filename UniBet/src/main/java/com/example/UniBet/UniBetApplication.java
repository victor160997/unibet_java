package com.example.UniBet;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.UniBet.model.ETipoResultado;
import com.example.UniBet.model.Jogo;
import com.example.UniBet.model.Time;
import com.example.UniBet.model.Usuario;
import com.example.UniBet.model.dao.JogoDAO;
import com.example.UniBet.model.dao.TimeDAO;
import com.example.UniBet.model.dao.UsuarioDAO;

@SpringBootApplication // (exclude={DataSourceAutoConfiguration.class})
public class UniBetApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UniBetApplication.class, args);
	}

	@Autowired
	TimeDAO dTime;
	@Autowired
	UsuarioDAO dUser;
	@Autowired
	JogoDAO dJogo;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("###### iniciando carregamento dos dados");

		Time time = new Time(1, "São Paulo FC", null, null);
		Time time1 = new Time(2, "Flamengo", null, null);
		dTime.save(time);
		dTime.save(time1);

		Usuario u = new Usuario(1, "Zezin da Silva",
				"ze", "123", "ze@ze", 0, true, null);
		Usuario u1 = new Usuario(2, "Pedrin Gui",
				"pe", "123", "pe@pe", 10000, false, null);
		dUser.save(u);
		dUser.save(u1);

		Jogo j1 = new Jogo(1, LocalDateTime.of(2024, 04, 25, 20, 30), 0.8, 0.2, 0.1, time, time1,
				0, 0,
				ETipoResultado.AGUARDANDO);
		Jogo j2 = new Jogo(2, LocalDateTime.now(), 0.5, 0.7, 0.3, time, time1,
				0, 0,
				ETipoResultado.VITORIA_A);

		dJogo.save(j1);
		dJogo.save(j2);

	}

}