package com.gft.gestaoprojetos.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Linguagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy ="linguagem")
	private List<Desenvolvedor> desenvolvedores;
	
	@NotEmpty(message = "É necessário incluir o nome.")
	private String nome;
	
	public Linguagem() {
		
	}
	
	public Linguagem(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	
}
