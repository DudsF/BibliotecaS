package models;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Livro {
	private Long id;
	private String titulo;
	private String autor;
	private String editora;
	@DateTimeFormat(pattern="yyyy")
	private static Calendar anoPub;
	private String edicao;

	@Override
	public String toString() {
		return "livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", editoraF=" + editora + ", anoPub=" + anoPub + ", edicao=" + edicao
				+ "]";
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public static Calendar getAnoPub() {
		return anoPub;
	}

	public void setAnoPub(Calendar anoPub) {
		Livro.anoPub = anoPub;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
}
