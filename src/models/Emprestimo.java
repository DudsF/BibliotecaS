package models;

import java.util.Calendar;


public class Emprestimo {
	private Long id; 
	private Aluno alunoID;
	private Livro livroID;
	private Calendar dataEmprestimo;
	private Calendar dataDevolucao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return alunoID;
	}
	public void setAluno(Aluno aluno) {
		this.alunoID = aluno;
	}
	public Livro getLivro() {
		return livroID;
	}
	public void setLivro(Livro livro) {
		this.livroID = livro;
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