package com.gft.gestaoprojetos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.gestaoprojetos.entities.Linguagem;

@Repository
public interface LinguagemRepository extends JpaRepository<Linguagem, Long>{

	Linguagem findByNome(String nome);
}
