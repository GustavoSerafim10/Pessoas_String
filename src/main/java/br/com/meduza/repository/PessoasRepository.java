package br.com.meduza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meduza.model.Pessoas;

public interface PessoasRepository extends JpaRepository<Pessoas, Long>{
	
}
