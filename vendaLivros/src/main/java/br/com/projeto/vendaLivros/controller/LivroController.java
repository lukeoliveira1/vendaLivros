package br.com.projeto.vendaLivros.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.vendaLivros.controller.dto.LivroDto;
import br.com.projeto.vendaLivros.modelo.Livro;
import br.com.projeto.vendaLivros.repository.CategoriaRepository;
import br.com.projeto.vendaLivros.repository.LivroRepository;
import br.com.projeto.vendaLivros.service.LivroService;

@RestController
@RequestMapping("/livros") // url dos comandos desse Controller
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroService livroService;

	@GetMapping
	@Cacheable(value = "listaDeLivros") 
	public ResponseEntity<List<Livro>> listar() {

		return ResponseEntity.ok(livroRepository.findAll());
	}
	
	@GetMapping("/{id}")
	@Cacheable(value = "listaDeLivros") 
	public ResponseEntity<Livro> listarPorId(@PathVariable("id") Long id) {
		//pra MVC Livro livro
		//pathVariable pq to passando o ID na url "/livros/id"
		
		Optional<Livro> livro = livroRepository.findById(id); 
		//faz a consulta se tem esse livro pelo id 
		
		if(livro.isPresent()) {
		return ResponseEntity.ok(livro.get());
		} 
		
		return ResponseEntity.notFound().build();
		//criar erro 404
	}
	
	@PostMapping("/")
	@CacheEvict(value = "listaDeLivros", allEntries = true)
	public ResponseEntity<?> cadastrar(@Valid Livro livro, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			//se entrar aqui tem erro
			
			//dizer pro usuario o tipo de erro
			//chave e valor
			Map<String, String> erros = new HashMap<String, String>();
			for(FieldError erro: bindingResult.getFieldErrors()) {
				//pegar todos erros e colocar dentro do HashMap
				erros.put(erro.getField(), erro.getDefaultMessage());				
			}
			return ResponseEntity.unprocessableEntity().body(erros);
		}
		
		livroService.salvar(livro);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<LivroDto> editar(@PathVariable Long id, @Valid LivroDto livroDto) {

		Optional<Livro> optional = livroRepository.findById(id);
		
		if (optional.isPresent()) {
			Livro livro = optional.get();
			livro.setTitulo(livroDto.getTitulo());
			livro.setValor(livroDto.getValor());
			livro.setAnoPublicacao(livroDto.getAnoPublicacao());
			livro.setCategoria(livroDto.getCategoria());
			livro.setNomeEditora(livroDto.getNomeEditora());

			livroService.salvar(livro);
			
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}
	
	
	
	

}
