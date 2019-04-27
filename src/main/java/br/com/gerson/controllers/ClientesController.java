package br.com.gerson.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gerson.models.Clientes;
import br.com.gerson.services.ClientesService;
import br.com.gerson.services.ServicosService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private ClientesService clientesService;
	
	@Autowired
	private ServicosService servicosService;

	@GetMapping
	public ModelAndView listAll(Clientes clientes) {
		ModelAndView mv = new ModelAndView("/lista-clientes");
		mv.addObject("cli", clientesService.listAll());
		return mv;
	}

	@GetMapping("/consulta")
	public ModelAndView listWith(Clientes clientes) {
		ModelAndView mv = new ModelAndView("/lista-clientes");
		mv.addObject("cli", clientesService.listWith(clientes));
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novoCliente(Clientes clientes) {
		ModelAndView mv = new ModelAndView("/novos-clientes");
		mv.addObject("serv", servicosService.listAll());
		
		mv.addObject(clientes);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView atualizarCliente(@PathVariable("id") Clientes clientes, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("/novos-clientes");
		mv.addObject("serv", servicosService.listAll());
		mv.addObject(clientes);
		return mv;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Clientes clientes, RedirectAttributes attributes) {
		clientesService.delete(clientes);
		attributes.addFlashAttribute("mensagem", "Cliente " + clientes.getNome() + " excluído(a) com sucesso!");
		return "redirect:/clientes";
	}

	@PostMapping("/add")
	public ModelAndView add(@Valid Clientes clientes, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {

			return novoCliente(clientes);

		}

		clientesService.add(clientes);
		attributes.addFlashAttribute("mensagem", "Dados do(a) cliente " + clientes.getNome() + " ,salvos com sucesso!");

		return new ModelAndView("redirect:/clientes");
	}

}
