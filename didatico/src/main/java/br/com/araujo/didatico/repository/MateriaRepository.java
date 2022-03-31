package br.com.araujo.didatico.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.araujo.didatico.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>{
	
	Optional<Materia> findById(Long id);
	List<Materia> findByNomeLike(String nome);

}
