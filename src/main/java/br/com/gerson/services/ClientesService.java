package br.com.gerson.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerson.models.Clientes;
import br.com.gerson.repositories.ClientesRepository;

@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	public List<Clientes> listAll(){
		return clientesRepository.findAll();
	}
	
	public List<Clientes> listWith(Clientes clientes){
		
		if(!clientes.getNome().isEmpty()) {
			
			return clientesRepository.findByNomeIgnoreCaseContaining(clientes.getNome());
			
		}
		
		return listAll();
	}
	
	public void add(Clientes clientes) {
		clientesRepository.save(clientes);
	}
	
	public void delete(Clientes clientes) {
		clientesRepository.delete(clientes);
	}

}
