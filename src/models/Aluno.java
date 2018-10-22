package models;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Aluno {

		private Long id;
		private String matricula;
		private String nome;
		private String CPF;
		@DateTimeFormat(pattern="dd/MM/yyyy")
		private Calendar dataNascimento;
		private String endereco;

		@Override
		public String toString() {
			return "aluno [id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", CPF=" + CPF + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco
					+ "]";
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.nome = matricula;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCPF() {
			return CPF;
		}

		public void setCPF(String CPF) {
			this.CPF = CPF;
		}


		public Calendar getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(Calendar dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

	}

