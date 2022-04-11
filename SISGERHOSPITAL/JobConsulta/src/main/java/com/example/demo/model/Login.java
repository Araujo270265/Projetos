package com.example.demo.model;

public class Login {
	
	private String nome;
	private String senha;
	private Integer grupo;
	
	public Login(String nome, String senha, Integer grupo) {
		this.nome = nome;
		this.senha = senha;
		this.grupo = grupo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getGrupo() {
		return grupo;
	}
	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}
	
	

}
