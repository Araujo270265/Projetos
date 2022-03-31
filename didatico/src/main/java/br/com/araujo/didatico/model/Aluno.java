package br.com.araujo.didatico.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aluno", schema = "didatico")
public class Aluno implements Serializable {

	private static final long serialVersionUID = -2186946921428917617L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", length = 50, insertable = true, updatable = true)
	private String nome;

	@Column(name = "idade_aluno", length = 2, insertable = true, updatable = true)
	private Integer idade;

	@Column(name = "email_aluno", length = 50, insertable = true, updatable = true)
	private String email;

	@Column(name = "nascimento_aluno", length = 30, insertable = true, updatable = true)
	private LocalDateTime dataNsacimento;

	@Column(name = "ativo", length = 1, insertable = true, updatable = true)
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

	@Override
	public int hashCode() {
		return Objects.hash(nome, ativo, dataNsacimento, email, id, idade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(ativo, other.ativo)
				&& Objects.equals(dataNsacimento, other.dataNsacimento) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(idade, other.idade);
	}

}
