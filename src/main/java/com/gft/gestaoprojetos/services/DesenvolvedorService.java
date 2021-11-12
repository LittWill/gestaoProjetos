package com.gft.gestaoprojetos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.gft.gestaoprojetos.entities.Desenvolvedor;
import com.gft.gestaoprojetos.repositories.DesenvolvedorRepository;

@Service
public class DesenvolvedorService {

	@Autowired
	DesenvolvedorRepository desenvolvedorRepository;

	public Desenvolvedor salvar(Desenvolvedor desenvolvedor) {
		return desenvolvedorRepository.save(desenvolvedor);
	}
	
	public List<Desenvolvedor> listarDesenvolvedores(String nome, String siglaNome) {
		if (nome != null || siglaNome != null) {
			return listarDesenvolvedoresPorNomeESigla(nome, siglaNome);
		}
		return listarTodosDesenvolvedores();
	}

	public List<Desenvolvedor> listarTodosDesenvolvedores() {
		return desenvolvedorRepository.findAll();
	}
	
	private List<Desenvolvedor> listarDesenvolvedoresPorNomeESigla(String nome, String siglaNome) {
		return desenvolvedorRepository.findByNomeContainsAndSiglaNomeContains(nome, siglaNome);
	}

	public Desenvolvedor obterDesenvolvedor(Long id) throws Exception {
		Optional<Desenvolvedor> desenvolvedor = desenvolvedorRepository.findById(id);

		if (desenvolvedor.isEmpty()) {
			throw new Exception("Desenvolvedor não encontrado!");
		}

		return desenvolvedor.get();
	}

	public void excluir(Long id) {
		desenvolvedorRepository.deleteById(id);
	}

	public boolean emailExistente(Desenvolvedor desenvolvedor) {
		if (desenvolvedor.getId() != null) {
			Desenvolvedor desenvolvedorOriginal = desenvolvedorRepository.getById(desenvolvedor.getId());
			if (desenvolvedor.getEmail().equals(desenvolvedorOriginal.getEmail())) {
				return false;
			}

		}
		return desenvolvedorRepository.findByEmail(desenvolvedor.getEmail()) != null;
	}

	public boolean siglaExistente(Desenvolvedor desenvolvedor) {
		if (desenvolvedor.getId() != null) {
			Desenvolvedor desenvolvedorOriginal = desenvolvedorRepository.getById(desenvolvedor.getId());
			if (desenvolvedor.getSiglaNome().equals(desenvolvedorOriginal.getSiglaNome())) {
				return false;
			}
		}
		
		return desenvolvedorRepository.findBySiglaNome(desenvolvedor.getSiglaNome()) != null;
	}


}
