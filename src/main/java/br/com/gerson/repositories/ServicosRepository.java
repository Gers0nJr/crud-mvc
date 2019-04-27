package br.com.gerson.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerson.models.Servicos;

@Repository
public interface ServicosRepository extends JpaRepository<Servicos, Integer>{
	
	List<Servicos> findByNomeIgnoreCaseContaining(String nome);

}
