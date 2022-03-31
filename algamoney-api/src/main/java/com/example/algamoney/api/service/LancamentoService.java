package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.service.Exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaService pessoaService; 
	
	public Optional<Lancamento> findId(Long codigo) {
		Optional<Lancamento> lancamentoPesquisa = lancamentoRepository.findById(codigo);
		return lancamentoPesquisa; 
	}
	
	@SuppressWarnings("null")
	public Lancamento salvar (Lancamento lancamento ) {

		Optional<Pessoa> pessoa = pessoaService.findId(lancamento.getPessoa().getCodigo());
		
		if (pessoa != null || pessoa.get().getAtivo() != false)  {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}
	
}
