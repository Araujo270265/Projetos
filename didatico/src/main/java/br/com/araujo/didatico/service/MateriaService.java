package br.com.araujo.didatico.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.araujo.didatico.model.Materia;
import br.com.araujo.didatico.repository.MateriaRepository;
import br.com.araujo.didatico.repository.ProfessorRepository;

@Service
public class MateriaService {

	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	public List<Materia> listar() {
		return materiaRepository.findAll();
	}

	public Materia findById(Long id) {
		return materiaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public List<Materia> findByNome(String nome) {
		return materiaRepository.findByNomeLike("%" + nome + "%");
	}

	@Transactional
	public Materia save(Materia materia) {

		return materiaRepository.save(materia);
	}

	@Transactional
	public Materia update(Long id, Materia materia) {
		Materia entity = materiaRepository.getById(id);
		entity.setNome(materia.getNome());
		entity.setAtivo(materia.getAtivo());
		return materiaRepository.save(entity);
	}

	@Transactional
	public void delete(Long id) {
		materiaRepository.deleteById(id);
	}

}
