package models;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Emprestimo {

	private String matricula;
	private String titulo;
	@DateTimeFormat(pattern="dd/MM/yyyy")
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