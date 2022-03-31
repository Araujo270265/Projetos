package br.com.araujo.didatico.dto;

import java.time.LocalDateTime;

public class AlunoDTO {

	private Long id;
	private String nome;
	private Integer idade;
	private String email;
	private LocalDateTime dataNsacimento;
	private Boolean ativo;
	
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
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getDataNsacimento() {
		return dataNsacimento;
	}
	public void setDataNsacimento(LocalDateTime dataNsacimento) {
		this.dataNsacimento = dataNsacimento;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	
}
