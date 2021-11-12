package com.gft.gestaoprojetos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.gestaoprojetos.entities.Desenvolvedor;

@Repository
public interface DesenvolvedorRepository extends JpaRepository <Desenvolvedor, Long>{

	List <Desenvolvedor> findByNomeContains(String nome);
	List <Desenvolvedor> findByNomeContainsAndSiglaNomeContains(String nome, String siglaNome);
	Desenvolvedor findBySiglaNome(String siglaNome);
	Desenvolvedor findByEmail(String email);
	
}