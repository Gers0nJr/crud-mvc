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

import br.com.gerson.models.Servicos;
import br.com.gerson.services.ServicosService;

@Controller
@RequestMapping("/servicos")
public class ServicosController {

	@Autowired
	private ServicosService servicosService;

	@GetMapping
	public ModelAndView listAll(Servicos servicos) {
		ModelAndView mv = new ModelAndView("/lista-servicos");
		mv.addObject("serv", servicosService.listAll());
		return mv;
	}

	@GetMapping("/consulta")
	public ModelAndView listWith(Servicos servicos) {
		ModelAndView mv = new ModelAndView("/lista-servicos");
		mv.addObject("serv", servicosService.listWith(servicos));
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novoServicos(Servicos servicos) {
		ModelAndView mv = new ModelAndView("/novos-servicos");
		mv.addObject(servicos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView atualizarServicos(@PathVariable("id") Servicos servicos, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("/novos-servicos");
		mv.addObject(servicos);

		return mv;
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") Servicos servicos, RedirectAttributes attributes) {
		servicosService.delete(servicos);
		attributes.addFlashAttribute("mensagem", servicos.getNome() + " excluído com sucesso!");
		return "redirect:/servicos";
	}

	@PostMapping("add")
	public ModelAndView add(@Valid Servicos servicos, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {

			return novoServicos(servicos);

		}

		servicosService.add(servicos);
		attributes.addFlashAttribute("mensagem", "Serviço " + servicos.getNome() + " ,salvos com sucesso!");

		return new ModelAndView("redirect:/servicos");
	}

}
