package br.com.araujo.didatico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "professor", schema = "didatico")
public class Professor implements Serializable {

	private static final long serialVersionUID = -2288259438195290912L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Campo é Obrigatorio")
	@NotBlank(message = "Campo é Obrigatorio")
	@Column(name = "nome_professor", length = 100, insertable = true, updatable = true)
	private String nome;
	
	@Column(name = "ativo", length = 1, insertable = true, updatable = true)
	private Boolean Ativo;
		
	@ManyToOne
	@JoinColumn(name="materia_id")
	private Materia materia;
	
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return Ativo;
	}

	public void setAtivo(Boolean ativo) {
		Ativo = ativo;
	}

	public Boolean getIsLeonardo() {

		if (nome == "Leonardo") {
			return true;
		} else {
			return false;
		}
	}

}
