package br.com.araujo.didatico.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.araujo.didatico.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	Optional<Professor> findById(Long id);
	List<Professor> findByNomeLike(String nome);

}
