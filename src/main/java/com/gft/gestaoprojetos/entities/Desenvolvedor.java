package com.gft.gestaoprojetos.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Desenvolvedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "É necessário incluir o nome.")
	private String nome;
	
	@NotEmpty(message = "É necessário incluir a sigla.")
	@Size(min = 4, max = 4, message = "A sigla deve possuir 4 letras.")
	private String siglaNome;

	@Email
	private String email;
	
	@Digits(fraction = 2, integer = 10)
	private BigDecimal salarioMensal;
	
	public BigDecimal getSalarioMensal() {
		return salarioMensal;
	}

	public void setSalarioMensal(BigDecimal salarioMensal) {
		this.salarioMensal = salarioMensal;
	}
	@ManyToOne
	private Linguagem linguagem;
	
	

	public Desenvolvedor() {
		
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
	public String getSiglaNome() {
		return siglaNome;
	}
	public void setSiglaNome(String siglaNome) {
		this.siglaNome = siglaNome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Linguagem getLinguagem() {
		return linguagem;
	}
	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}
	
	
}
