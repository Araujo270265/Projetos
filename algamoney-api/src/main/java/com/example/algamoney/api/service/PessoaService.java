package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Pessoa update(Long codigo, Pessoa pessoa) {
		Pessoa entity = pessoaRepository.getOne(codigo);
		entity.setNome(pessoa.getNome());
		entity.setAtivo(pessoa.getAtivo());
		entity.setEndereco(pessoa.getEndereco());
		return pessoaRepository.save(entity);
	}
	
	public Optional<Pessoa> findId(Long codigo) {
		Optional<Pessoa> pessoaPesquisa = pessoaRepository.findById(codigo);
		return pessoaPesquisa; 
	}
	
	public Boolean validaNomePessoa(Pessoa pessoa) {
		if ((pessoa.getNome() == null) || (pessoa.getNome().isEmpty())) {
			return false;
		}else {
			return true;
		}
	}
	
	public Boolean validaNomeAtivo(Pessoa pessoa) {
		if (pessoa.getAtivo() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public Boolean validaNomeCep(Pessoa pessoa) {
		if (pessoa.getEndereco().getCep().length() > 9) {
			return false;
		}else {
			return true;
		}
	}
	
	
}
