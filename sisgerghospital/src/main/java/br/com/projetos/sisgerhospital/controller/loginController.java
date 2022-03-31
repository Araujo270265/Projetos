package br.com.projetos.sisgerhospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.projetos.sisgerhospital.model.Login;
import br.com.projetos.sisgerhospital.service.LoginService;

@RestController
@RequestMapping("/login")
public class loginController {
	
	@Autowired
	private LoginService loginservice ;

	@GetMapping
	public List<Login> listaLogin() {
		return loginservice.findAll();
	}
}
