package com.gft.gestaoprojetos.services;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.gestaoprojetos.entities.Linguagem;
import com.gft.gestaoprojetos.entities.Projeto;
import com.gft.gestaoprojetos.repositories.ProjetoRepository;

@Service
public class ProjetoService {
	public static DateTimeFormatter padraoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Autowired
	private ProjetoRepository projetoRepository;

	public Projeto salvarProjeto(Projeto projeto) {
		return projetoRepository.save(projeto);
	}

	public List<Projeto> listarProjetos() {
		return projetoRepository.findAll();
	}

	public Projeto obterProjeto(Long id) throws Exception {
		Optional<Projeto> projeto = projetoRepository.findById(id);

		if (projeto.isEmpty()) {
			throw new Exception("Projeto não encontrado!");
		}

		return projeto.get();
	}

	public void excluirProjeto(Long id) {
		projetoRepository.deleteById(id);

	}
}
