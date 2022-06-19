package br.com.projeto.vendaLivros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.vendaLivros.modelo.Livro;
import br.com.projeto.vendaLivros.repository.LivroRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private LivroRepository livroRepository;

	@GetMapping
	public String home(Model model) {

		List<Livro> livros = livroRepository.findAll();
		model.addAttribute("livros", livros);
		return "home";
	}
	
}
