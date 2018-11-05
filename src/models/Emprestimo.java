package models;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Emprestimo {

	private Aluno aluno;
	private Livro livro;
	private Calendar dataEmprestimo;
	private Calendar dataDevolucao;
	
	@Override
	public String toString() {
		return "aluno [matricula=" + matricula + ", titulo=" + titulo + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao	+ "]";
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTituloDoLivro() {
		return titulo;
	}

	public void setTituloDoLivro(String tituloDoLivro) {
		titulo = tituloDoLivro;
	}

	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Calendar dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
}