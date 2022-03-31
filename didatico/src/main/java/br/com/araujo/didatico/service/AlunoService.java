package br.com.araujo.didatico.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.araujo.didatico.model.Aluno;
import br.com.araujo.didatico.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	@Transactional
	public Aluno create(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	public Aluno findById(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public List<Aluno> findByNome(String nome) {
		return alunoRepository.findByNomeLike("%" + nome + "%");
	}

	@Transactional
	public Aluno update(Long id, Aluno aluno) {
		Aluno entity = alunoRepository.getById(id);
		entity.setNome(aluno.getNome());
		entity.setEmail(aluno.getEmail());
		entity.setIdade(aluno.getIdade());
		entity.setDataNsacimento(aluno.getDataNsacimento());
		entity.setAtivo(aluno.getAtivo());
		return alunoRepository.save(entity);
	}

	@Transactional
	public void delete(Long id) {
		alunoRepository.deleteById(id);
	}

}
