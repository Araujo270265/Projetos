package br.com.araujo.didatico.dto;

public class ProfessorDTO {
	
	private String nome;
	private Boolean ativo;
	private Long materiaId;
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Long getMateriaId() {
		return materiaId;
	}
	public void setMateriaId(Long materiaId) {
		this.materiaId = materiaId;
	}
	
	
}
