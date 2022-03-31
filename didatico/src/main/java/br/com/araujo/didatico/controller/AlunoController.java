package br.com.araujo.didatico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.araujo.didatico.error.ErrorResponse;
import br.com.araujo.didatico.model.Aluno;
import br.com.araujo.didatico.service.AlunoService;

@RestController
@RequestMapping("aluno")
public class AlunoController {

	@Autowired
	private AlunoService serviceAluno;

	@GetMapping
	public List<Aluno> getFonteAll() {
		return serviceAluno.listar();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable String id) {
		try {
			return ResponseEntity.ok(serviceAluno.findById(Long.parseLong(id)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponse("O argumento " + id + "é inválido"));
		}
	}

	@GetMapping("/nome")
	public ResponseEntity<List<Aluno>> getAlunoID(@RequestParam String nome) {
		return new ResponseEntity<List<Aluno>>(serviceAluno.findByNome(nome), HttpStatus.OK);
	}

	@PostMapping
	public Aluno create(@RequestBody Aluno aluno) {
		return serviceAluno.create(aluno);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@RequestBody Aluno aluno, @PathVariable String id) {
		try {
			return ResponseEntity.ok().body(serviceAluno.update(Long.parseLong(id), aluno));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponse("O argumento " + id + "é inválido"));
		}
	}

	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		serviceAluno.delete(id);
	}
}
