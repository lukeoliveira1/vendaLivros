package br.com.projeto.vendaLivros.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.vendaLivros.controller.dto.LivroDto;
import br.com.projeto.vendaLivros.modelo.Livro;
import br.com.projeto.vendaLivros.repository.LivroRepository;
import br.com.projeto.vendaLivros.service.LivroService;

@Controller
@RequestMapping("livro") // configuração da rota
public class LivroMvcController {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private LivroService livroService;

	@GetMapping("formulario")
	public String formulario(LivroDto livroDto) {
		return "livro/formulario";
	}

	@PostMapping("novo")
	public String novo(@Valid LivroDto livros, BindingResult binding) {
		if (binding.hasErrors()) {
			return "livro/formulario";
		}
		Livro livro = livros.toPedido();
		livroService.salvar(livro);

		return "redirect:/home";
	}

	@PutMapping("editar")
	public String editar(@Valid Livro livros) {
		return "redirect:/home";
	}
	
	@GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
  
        Livro livro = livroService.getLivroById(id);
        model.addAttribute("livro", livro);
        return "livro/formUpdate";
    }

	@GetMapping("deletar/{id}")
	public String deletar(@PathVariable( value = "id") long id) {

        Livro livro = livroService.getLivroById(id);
		livroService.deletar(livro);
		return "redirect:/home";
	}

}
