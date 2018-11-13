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
	public boolean qtdEmprestimos(Aluno aluno) throws SQLException {

		try {
			PreparedStatement stmt = connection
					.prepareStatement("select * from emprestimo where aluno = ? and dataDevolucao IS NULL;");
			stmt.setLong(1, aluno.getId());
			ResultSet rs = stmt.executeQuery();
			int cont = 0;
			while (rs.next()) {
				cont++;
			}
			stmt.close();
			if (cont > 2) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public List<Emprestimo> getListaAbertos() {
		try {

			List<Emprestimo> emprestimo = new ArrayList<Emprestimo>();
			PreparedStatement stmt = connection
					.prepareStatement("select * from emprestimo where dataDevolucao is null;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Emprestimo emp = new Emprestimo();
				
				emp.setId(rs.getLong("id"));
				Livro livro = new LivroDAO().getById(rs.getLong("livro"));
				Aluno aluno = new AlunoDAO().getById(rs.getLong("aluno"));

				emp.setLivro(livro);
				emp.setAluno(aluno);
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmprestimo"));
				emp.setDataEmprestimo(data);

				if (rs.getDate("dataDevolucao") != null) {
					Calendar dataDevolucao = Calendar.getInstance();
					dataDevolucao.setTime(rs.getDate("dataDevolucao"));
					emp.setDataDevolucao(dataDevolucao);
				}

				emprestimo.add(emp);
			}

			stmt.close();
			return emprestimo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Emprestimo> getListaAtraso() {
		try {

			List<Emprestimo> emprestimo = new ArrayList<Emprestimo>();
			PreparedStatement stmt = connection
					.prepareStatement("select * from emprestimo where dataDevolucao is null and dataEmprestimo < ?;");
			Emprestimo emp = new Emprestimo();

			Calendar dataEmprestimo = Calendar.getInstance();
			emp.setDataEmprestimo(dataEmprestimo);

			dataEmprestimo.add(Calendar.DAY_OF_YEAR, -14);

			stmt.setDate(1, new java.sql.Date(emp.getDataEmprestimo().getTimeInMillis()));

			ResultSet rs = stmt.executeQuery();

			rs.close();
			stmt.close();
			return emprestimo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Emprestimo> getLista() {
		try {

			List<Emprestimo> emprestimo = new ArrayList<Emprestimo>();
			PreparedStatement stmt = connection.prepareStatement("select * from emprestimo;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Emprestimo emp = new Emprestimo();
				
				emp.setId(rs.getLong("id"));
				Aluno aluno = new AlunoDAO().getById(rs.getLong("aluno"));
				emp.setAluno(aluno);
				Livro livro = new LivroDAO().getById(rs.getLong("livro"));
				emp.setLivro(livro);

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmprestimo"));
				emp.setDataEmprestimo(data);

				if (rs.getDate("dataDevolucao") != null) {
					Calendar data2 = Calendar.getInstance();
					data2.setTime(rs.getDate("dataDevolucao"));
					emp.setDataDevolucao(data2);
				}
				emprestimo.add(emp);
			}
			rs.close();
			stmt.close();
			return emprestimo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public boolean devolucao(Emprestimo emprestimo) {
		String sql = "update emprestimo set dataDevolucao=? where id=?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(2, emprestimo.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Emprestimo getEmprestimoByID(Long id) {
		try {

			Emprestimo emprestimo = null;
			PreparedStatement stmt = this.connection.prepareStatement("select * from emprestimo where id=?;");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			AlunoDAO alunoDAO = new AlunoDAO();
			LivroDAO livroDAO = new LivroDAO();
			while (rs.next()) {
				emprestimo = new Emprestimo();
				emprestimo.setId(rs.getLong("id"));
				emprestimo.setAluno(alunoDAO.getById(id));
				emprestimo.setLivro(livroDAO.getById(id));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataDevolucao"));
				emprestimo.setDataEmprestimo(data);
			}
			rs.close();
			stmt.close();
			return emprestimo;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

}


	
