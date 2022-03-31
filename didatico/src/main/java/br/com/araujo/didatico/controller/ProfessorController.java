package br.com.araujo.didatico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.araujo.didatico.error.ErrorResponse;
import br.com.araujo.didatico.model.Professor;
import br.com.araujo.didatico.service.ProfessorService;

@RestController
@RequestMapping("professor")
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	

	@GetMapping
	public List<Professor> getFonteAll() {
		return professorService.listar();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable String id) {
		try {
			return ResponseEntity.ok(professorService.findById(Long.parseLong(id)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponse("O argumento " + id + "é inválido"));
		}
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Professor create(@RequestBody Professor professor) {
		return professorService.save(professor);
	}
	
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<?> update(@RequestBody ProfessorDTO professorDTO, @PathVariable Long id) {
//		try {
//			return ResponseEntity.ok().body(professorService.save(professorDTO, id));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ErrorResponse("O argumento " + id + "é inválido"));
//		}
//	}


	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		professorService.delete(id);
	}

}
