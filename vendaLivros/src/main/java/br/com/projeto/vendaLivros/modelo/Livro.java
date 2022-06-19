package br.com.projeto.vendaLivros.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Livro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private float valor;
	private String anoPublicacao;
	private String nomeEditora;
	@ManyToOne(cascade = CascadeType.ALL)
	//salvando categoria junto com livro
	private Categoria categoria;
	
	public Livro() {
		
	}
	
	public Livro(String titulo, float valor, String anoPublicacao, String nomeEditora, Categoria categoria) {
		super();
		this.titulo = titulo;
		this.valor = valor;
		this.anoPublicacao = anoPublicacao;
		this.nomeEditora = nomeEditora;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
