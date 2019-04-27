package br.com.gerson.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerson.models.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer>{
	
	List<Clientes> findByNomeIgnoreCaseContaining(String nome);

}
