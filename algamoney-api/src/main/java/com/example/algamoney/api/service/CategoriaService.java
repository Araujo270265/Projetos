package com.example.algamoney.api.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional
	public Categoria update(Long codigo, Categoria categoria) {
		Categoria entity = categoriaRepository.getOne(codigo);
		entity.setNome(categoria.getNome());
		return categoriaRepository.save(entity);
	}

	public Optional<Categoria> findId(Long codigo) {
		Optional<Categoria> categoriaPesquisa = categoriaRepository.findById(codigo);
		return categoriaPesquisa; 
	}
	
	public Boolean criar(Categoria categoria) {
		if ((categoria.getNome() == null) || (categoria.getNome().isEmpty())) {
			return false;
		}else {
			return true;
		}
	}
}


