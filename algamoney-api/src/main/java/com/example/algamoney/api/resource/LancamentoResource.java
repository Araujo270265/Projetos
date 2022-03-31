package com.example.algamoney.api.resource;


import java.util.*;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import com.example.algamoney.api.service.LancamentoService;
import com.example.algamoney.api.service.Exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarpeloCodigo(@PathVariable Long codigo) {
		Optional<Lancamento> lancamento = lancamentoService.findId(codigo);

		if (lancamento.isEmpty()) {
			ResponseEntity.notFound().build();
			return ResponseEntity.ok().body("Não existe este codigo " + codigo);
		} else {
			return ResponseEntity.ok().body(lancamento);
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> criar(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
	
		Lancamento lancamentoSalva = lancamentoService.salvar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getCodigo())); 
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);

	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> remover(@PathVariable Long codigo) {
		Optional<Lancamento> lancamentoPesquisa = lancamentoService.findId(codigo);
		
		if (lancamentoPesquisa.isEmpty()) {
			ResponseEntity.notFound().build();
			return ResponseEntity.ok().body("Não existe este codigo de lançamento " + codigo);
		}else{
			lancamentoRepository.deleteById(codigo);
         return ResponseEntity.ok().body(lancamentoPesquisa.get().getDescricao() + " Foi excluida do cadastro!");
		}
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })	
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
			String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
	        String mensagemDesenvolvedor = ex.toString();
	        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
		
	}
}
