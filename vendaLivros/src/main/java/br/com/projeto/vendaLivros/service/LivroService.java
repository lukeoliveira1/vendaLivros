package br.com.projeto.vendaLivros.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.vendaLivros.modelo.Livro;
import br.com.projeto.vendaLivros.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	//usar esse pra salvar e atualizar/editar
	public void salvar(Livro livro) {
		// poderia implementar regra de negócio(verificar titulo do usuario esta em
		// maisculo, etc...)
		livroRepository.saveAndFlush(livro);
		// saveAndFlush salva e atualiza o objeto
	}

	public void deletar(Livro livro) {
		// poderia implementar regra de negócio(verificar titulo do usuario esta em
		// maisculo, etc...)
		livroRepository.delete(livro);
		// deletando
	}
	
	public Livro getLivroById(long id) {
        Optional<Livro> optionalCourse = livroRepository.findById(id);
        Livro livro = null;
        if (optionalCourse.isPresent()) {
        	livro = optionalCourse.get();
        } else {
            throw new RuntimeException("Course not found for id : " + id);
        }
        return livro;
    }
}
