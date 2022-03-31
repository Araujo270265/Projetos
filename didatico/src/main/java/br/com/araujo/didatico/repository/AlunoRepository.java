package br.com.araujo.didatico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.araujo.didatico.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	Optional<Aluno> findById(Long id);
	List<Aluno> findByNomeLike(String nome);

}
