package br.com.projetos.sisgerhospital.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.projetos.sisgerhospital.model.Login;
import br.com.projetos.sisgerhospital.repository.LoginRepository;

public class LoginService {
	
	@Autowired
	private LoginRepository loginrepository ;
	
	public List<Login> findAll() {
		List<Login> loginLista = loginrepository.findAll();
		return loginLista; 
	}
	

}
