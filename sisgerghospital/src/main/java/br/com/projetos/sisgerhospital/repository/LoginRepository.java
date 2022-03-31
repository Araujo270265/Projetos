package br.com.projetos.sisgerhospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetos.sisgerhospital.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

}
