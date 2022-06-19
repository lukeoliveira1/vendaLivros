package br.com.projeto.vendaLivros.controller.dto;

import javax.persistence.ManyToOne;

import br.com.projeto.vendaLivros.modelo.Categoria;
import br.com.projeto.vendaLivros.modelo.Livro;
import br.com.projeto.vendaLivros.repository.LivroRepository;

public class LivroDto {
	
	private Long id;
	private String titulo;
	private float valor;
	private String anoPublicacao;
	private String nomeEditora;
	private Categoria categoria;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public String getNomeEditora() {
		return nomeEditora;
	}
	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Livro toPedido() {
		Livro livro = new Livro();
		livro.setId(id);
		livro.setTitulo(titulo);
		livro.setValor(valor);
		livro.setAnoPublicacao(anoPublicacao);
		livro.setNomeEditora(nomeEditora);
		livro.setCategoria(categoria);
		return livro;
	}
	
	
}
