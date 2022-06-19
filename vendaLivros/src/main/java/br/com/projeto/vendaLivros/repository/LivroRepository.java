package br.com.projeto.vendaLivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.vendaLivros.modelo.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
