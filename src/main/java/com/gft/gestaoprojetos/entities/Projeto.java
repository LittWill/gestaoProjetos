package com.gft.gestaoprojetos.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.gft.gestaoprojetos.services.ProjetoService;

@Entity
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "É necessário incluir o nome.")
	private String nome;
	
	@NotEmpty(message = "É necessário incluir o apelido do projeto.")
	private String apelido;
	
	@ManyToMany
	private List <Desenvolvedor> desenvolvedores = new ArrayList<>();
	
	@ManyToOne
	private Linguagem linguagem;
	
	private LocalDate dataEntrega = LocalDate.now();
	
	@Digits(fraction = 2, integer = 10)
	private BigDecimal orcamento;

	public Projeto() {

	}

	public Projeto(Long id, String nome, String apelido, List<Desenvolvedor> desenvolvedores, Linguagem linguagem,
			LocalDate dataEntrega, BigDecimal orcamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.desenvolvedores = desenvolvedores;
		this.linguagem = linguagem;
		this.dataEntrega = dataEntrega;
		this.orcamento = orcamento;
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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public List<Desenvolvedor> getDesenvolvedores() {
		return desenvolvedores;
	}

	public void setDesenvolvedores(List<Desenvolvedor> desenvolvedores) {
		this.desenvolvedores = desenvolvedores;
	}

	public Linguagem getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}

	public LocalDate getDataEntrega() {
		return this.dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public BigDecimal getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}

}
