package br.com.gerson.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerson.models.Servicos;
import br.com.gerson.repositories.ServicosRepository;

@Service
public class ServicosService {
	
	@Autowired
	private ServicosRepository servicosRepository;
	
	public List<Servicos> listAll(){
		return servicosRepository.findAll();
	}
	
	public List<Servicos> listWith(Servicos servicos){
		
		if(!servicos.getNome().isEmpty()) {
			
			return servicosRepository.findByNomeIgnoreCaseContaining(servicos.getNome());
			
		}
		
		return listAll();
	}
	
	public void add(Servicos servicos) {
		servicosRepository.save(servicos);
	}
	
	public void delete(Servicos servicos) {
		servicosRepository.delete(servicos);
	}

}
