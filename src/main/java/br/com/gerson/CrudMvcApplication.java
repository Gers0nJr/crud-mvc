package br.com.gerson;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.gerson.models.Clientes;
import br.com.gerson.models.Servicos;
import br.com.gerson.models.Usuarios;
import br.com.gerson.models.enums.Sexo;
import br.com.gerson.repositories.ClientesRepository;
import br.com.gerson.repositories.ServicosRepository;
import br.com.gerson.repositories.UsuariosRepository;

@SpringBootApplication
public class CrudMvcApplication implements CommandLineRunner{
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private ServicosRepository servicosRepository;
	
	@Autowired
	private UsuariosRepository usuariosRepository;

	public static void main(String[] args) {
		SpringApplication.run(CrudMvcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Senha 123 gerada com bcrypt: " + new BCryptPasswordEncoder().encode("123"));
		
		Usuarios user1 = new Usuarios(null, "admin1", "123");
		Usuarios user2 = new Usuarios(null, "admin2", "123");
		Usuarios user3 = new Usuarios(null, "admin3", "123");
		
		usuariosRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		Servicos serv1 = new Servicos(null, "Pedreiro", "Responsável por construir e reparar casas e edíficios");
		Servicos serv2 = new Servicos(null, "Cozinheiro", "Responsável por prepara alimentos");
		Servicos serv3 = new Servicos(null, "Zelador", "Responsável por manter a conservação");
		Servicos serv4 = new Servicos(null, "Analista de Sistemas", "Responsável por analisar e levantar requisitos de sistemas");
		Servicos serv5 = new Servicos(null, "Padeiro", "Responsável por fabricar pão e outros alimentos de uma panificadora");
		Servicos serv6 = new Servicos(null, "Professor", "Responsável por ensinar");
		
		servicosRepository.saveAll(Arrays.asList(serv1, serv2, serv3, serv4, serv5, serv6));
		
		Clientes cli1 = new Clientes(null, "Maria Silva", LocalDate.of(1984,03,05), Sexo.FEMININO, "11 98078-8877", "Rua das Rosas 199");
		cli1.setServicos(Arrays.asList(serv1, serv2, serv5));
		
		Clientes cli2 = new Clientes(null, "João Batista", LocalDate.of(1982,07,22), Sexo.MASCULINO, "11 98765-0066", "Rua dos Cravos 1566");
		cli2.setServicos(Arrays.asList(serv3, serv2, serv5));
		
		Clientes cli3 = new Clientes(null, "Elias Oliveira", LocalDate.of(1986,10,02), Sexo.MASCULINO, "11 98877-8766", "Rua das Palmas 199");
		cli3.setServicos(Arrays.asList(serv4, serv2, serv6));
		
		clientesRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		
	}
}
