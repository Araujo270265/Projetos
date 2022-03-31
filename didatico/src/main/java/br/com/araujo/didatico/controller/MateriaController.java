package br.com.araujo.didatico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.araujo.didatico.error.ErrorResponse;
import br.com.araujo.didatico.model.Materia;
import br.com.araujo.didatico.service.MateriaService;

@RestController
@RequestMapping("materia")
public class MateriaController {
	
	@Autowired
	private MateriaService materiaService;
	
	@GetMapping
	public List<Materia> getFonteAll() {
		return materiaService.listar();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable String id) {
		try {
			return ResponseEntity.ok(materiaService.findById(Long.parseLong(id)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponse("O argumento " + id + "é inválido"));
		}
	}
	
	@PostMapping
	public Materia create(@RequestBody Materia materia) {
		return materiaService.save(materia);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@RequestBody Materia materia, @PathVariable String id) {
		try {
			return ResponseEntity.ok().body(materiaService.update(Long.parseLong(id), materia));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponse("O argumento " + id + "é inválido"));
		}
	}

	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		materiaService.delete(id);
	}

}
