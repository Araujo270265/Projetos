package br.com.araujo.didatico.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.araujo.didatico.model.Professor;
import br.com.araujo.didatico.repository.MateriaRepository;
import br.com.araujo.didatico.repository.ProfessorRepository;


@Service
public class ProfessorService {
	
	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	MateriaRepository materiaRepository;
	
	public List<Professor> listar() {
		return professorRepository.findAll();
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public List<Professor> findByNome(String nome) {
		return professorRepository.findByNomeLike("%" + nome + "%");
	}

	@Transactional
	public Professor save(Professor professor) {
		
//		Professor entity;
//	
//		if (id != null) {
//			 entity = professorRepository.getById(id);
//		}else {
//			 entity = new Professor(); 
//		}
//		
//		Materia materia = materiaRepository.findById(professorDTO.getMateriaId()).get();
//		
//		entity.setNome(professorDTO.getNome());
//		entity.setAtivo(professorDTO.getAtivo());
//		entity.setMateria(materia);
		return professorRepository.save(professor);

//		materia.setProfessorMateria(entity);
//		return entity;
	}
		
		
	
	@Transactional
	public void delete(Long id) {
		professorRepository.deleteById(id);
	}
	
	

}
