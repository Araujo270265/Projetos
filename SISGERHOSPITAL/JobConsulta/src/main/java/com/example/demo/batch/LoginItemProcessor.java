package com.example.demo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.example.demo.model.Login;

public class LoginItemProcessor implements ItemProcessor<Login, Login>{
	
	private static final Logger log = LoggerFactory.getLogger(LoginItemProcessor.class);
	
	@Override
	public Login process(Login login) throws Exception {
		final String nome = login.getNome().toUpperCase();
	    final String senha = login.getSenha().toUpperCase();
	    final Integer grupo = login.getGrupo();
	    final Login transformed = new Login(nome, senha, grupo);
	    log.info("Converting (" + login + ") into (" + transformed + ")");

        return transformed;
	}

}
