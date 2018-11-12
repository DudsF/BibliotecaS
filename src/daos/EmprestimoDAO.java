package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Aluno;
import models.Emprestimo;
import models.Livro;

public class EmprestimoDAO {
	private Connection connection;

	public EmprestimoDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public boolean inserir( Emprestimo emprestimo) {

		String sql = "insert into emprestimo (matricula, titulo, dataEmprestimo, dataDevolucao) values (?, ?, ?, ?);";
	
	try{
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, emprestimo.getAluno().getId());
		stmt.setLong(2, emprestimo.getLivro().getId());
		stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTimeInMillis()));

		stmt.execute();
		stmt.close();

	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}

	return true;
}

	public boolean verificarAluno(Long idAluno) {

		String sql = "select * from emprestimos where idAluno = ? and dataDevolucao IS NULL;";
		int qtdDeLivrosEmprestados = 0;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, idAluno);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				qtdDeLivrosEmprestados++;

			}
			stmt.close();

			if (qtdDeLivrosEmprestados >= 3) {
				
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	
	public boolean verificarLivro(Long idLivro) {
		String sql = "select * from emprestimos where idLivro = ? and dataDevolucao IS NULL;";
		int LivroEmprestado = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setLong(1, idLivro);
			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {
				LivroEmprestado++;
			}

			
			if (LivroEmprestado >= 1) {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public boolean devolucao(Emprestimo emprestimo) {

		String sql = "update emprestimos set dataDevolucao=? where idAluno=? and idLivro=?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(2, emprestimo.getAluno().getId());
			stmt.setLong(3, emprestimo.getLivro().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Emprestimo> pesquisarPorMatricula(int matriculaAluno) {
		List<Emprestimo> result = new ArrayList<>();
		String sql = "select * from emprestimos where idAluno = ?;";
		Aluno aluno = new AlunoDAO().getByMatricula(matriculaAluno);

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, aluno.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Emprestimo e = new Emprestimo();
				Livro livro = new Livro();
				Aluno alun = new Aluno();
				Calendar data = Calendar.getInstance();

				livro.setId(rs.getLong("idLivro"));
				aluno.setId(rs.getLong("idAluno"));
				data.setTime(rs.getDate("dataEmprestimo"));
				data.setTime(rs.getDate("dataDevolucao"));

				e.setAluno(aluno);
				e.setLivro(livro);
				e.setDataEmprestimo(data);
				e.setDataDevolucao(data);

				result.add(e);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Emprestimo> getEmprestimos() {
		List<Emprestimo> result = new ArrayList<>();

		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from emprestimos;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Emprestimo e = new Emprestimo();
				Aluno aluno = new AlunoDAO().getById(rs.getLong("Aluno"));
				Livro livro = new LivroDAO().getById(rs.getLong("idLivro"));
				e.setAluno(aluno);
				e.setLivro(livro);

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmprestimo"));
				e.setDataEmprestimo(data);

				
				if (rs.getDate("dataDevolucao") != null) {
					
					data.setTime(rs.getDate("dataDevolucao"));

					e.setDataDevolucao(data);

				} else {
					
					e.setDataDevolucao(null);
				}

				result.add(e);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Emprestimo> getEmprestimosAtrasados() {
		List<Emprestimo> result = new ArrayList<>();

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from emprestimos where dataDevolucao IS NULL and dataEmprestimo < ?;");
			Calendar data = Calendar.getInstance();
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Emprestimo e = new Emprestimo();
				Calendar dataEmprestimo = Calendar.getInstance();
				dataEmprestimo.setTime(rs.getDate("dataEmprestimo"));
				e.setDataEmprestimo(dataEmprestimo);
				Aluno a = new AlunoDAO().getById(rs.getLong("idAluno"));
				Livro l = new LivroDAO().getById(rs.getLong("idLivro"));
				e.setAluno(a);
				e.setLivro(l);

				result.add(e);


			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Emprestimo> getEmprestimosAtivos() {
		List<Emprestimo> result = new ArrayList<>();

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from emprestimos where dataDevolucao IS NULL;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Emprestimo e = new Emprestimo();
				Aluno a = new AlunoDAO().getById(rs.getLong("idAluno"));
				Livro l = new LivroDAO().getById(rs.getLong("idLivro"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmprestimo"));
				e.setDataEmprestimo(data);
				e.setAluno(a);
				e.setLivro(l);

				result.add(e);

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean remover(Long id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from emprestimos where id=?;");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	}

