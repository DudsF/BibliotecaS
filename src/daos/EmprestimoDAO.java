package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

		String sql = "insert into emprestimo (aluno, livro, dataEmprestimo) values ( ?, ?, ?);";
	
	try{
		
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		Calendar calen = Calendar.getInstance();
		Long calendario = calen.getTimeInMillis();
		
		stmt.setLong(1, emprestimo.getAluno().getId());
		stmt.setLong(2, emprestimo.getLivro().getId());
		stmt.setDate(3, new java.sql.Date(calendario));
		

		stmt.execute();
		stmt.close();

	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}

	return true;
}

	public boolean QuantEmprestimos(Aluno aluno) {
		
		String sql =  "select * from emprestimo where aluno = ? and dataDevolucao IS NULL;";
		int cont = 0;
		
		try {
			PreparedStatement stmt = connection
					.prepareStatement(sql);
		//stmt.setLong(1, aluno.getId());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			//Contagem de livro com o aluno.
			cont++;
		}
		
		if (cont >= 3) {
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}

	return true;

}
	public boolean QuantEmprestimos(Livro livro) {
		
		String sql =  "select * from emprestimo where livro = ? and dataDevolucao IS NULL;";
		int cont = 0;
		
		try {
			PreparedStatement stmt = connection
					.prepareStatement(sql);
		stmt.setLong(1, livro.getId());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			//Contagem das vezes o livro foi emprestado.
			cont++;
		}
		
		if (cont >= 1) {
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}

	return true;

}

	public List<Emprestimo> getAcessiveis() {
		List<Emprestimo> emprestimo = new ArrayList<Emprestimo>();
			
			try {
				
			PreparedStatement stmt = this.connection.prepareStatement("select * from emprestimo;");
			ResultSet rs = stmt.executeQuery();

			Calendar dataEmprestimo = Calendar.getInstance();
			stmt.setDate(1, new Date(dataEmprestimo.getTimeInMillis()-14 * 24 * 60 * 60 * 1000));
			

			while (rs.next()) {
				 Emprestimo emprestimo1 = new Emprestimo();
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("dataEmprestimo"));
					emprestimo1.setDataEmprestimo(data);
					Aluno aluno = new AlunoDAO().getById(rs.getLong("aluno"));
					Livro livro = new LivroDAO().getById(rs.getLong("livro"));
					emprestimo1.setAluno(aluno);
					emprestimo1.setLivro(livro);

					emprestimo.add(emprestimo1);

			}
			rs.close();

			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return emprestimo;
	}

	public List<Emprestimo> getAtrasados() {
	
			List<Emprestimo> emprestimo = new ArrayList<Emprestimo>();
	
			try {
				
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from emprestimo where dataDevolucao is null and dataEmprestimo < ?;");
			

			Calendar dataEmprestimo = Calendar.getInstance();
			stmt.setDate(1, new Date(dataEmprestimo.getTimeInMillis()-14 * 24 * 60 * 60 * 1000));
			ResultSet rs = stmt.executeQuery();
			
		 while (rs.next()){
			 Emprestimo emprestimo1 = new Emprestimo();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmprestimo"));
				emprestimo1.setDataEmprestimo(data);
				Aluno aluno = new AlunoDAO().getById(rs.getLong("aluno"));
				Livro livro = new LivroDAO().getById(rs.getLong("livro"));
				emprestimo1.setAluno(aluno);
				emprestimo1.setLivro(livro);

				emprestimo.add(emprestimo1);
		 
		 }

			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return emprestimo;
	}

	public List<Emprestimo> getLista() {
		try {

			List<Emprestimo> emprestimo = new ArrayList<Emprestimo>();
			PreparedStatement stmt = connection.prepareStatement("select * from emprestimo;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				emprestimo.add (formacaoEmprestimo(rs));
				
			}
			rs.close();
			stmt.close();
			return emprestimo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public boolean devolucao(Emprestimo emprestimo) {
		String sql = "update emprestimo set dataDevolucao=? where aluno=? and livro=?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(2, emprestimo.getAluno().getId());
			stmt.setLong(2, emprestimo.getLivro().getId());
			
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
			
			while (rs.next()) {
				emprestimo = formacaoEmprestimo(rs);
			}
			rs.close();
			stmt.close();
			return emprestimo;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}
	private Emprestimo formacaoEmprestimo(ResultSet rs) throws SQLException {
		Emprestimo emprestimo = new Emprestimo();

		Aluno aluno = new AlunoDAO().getById(rs.getLong("aluno"));
		emprestimo.setAluno(aluno);
		Livro livro = new LivroDAO().getById(rs.getLong("livro"));
		emprestimo.setLivro(livro);

		Calendar data = Calendar.getInstance();
		data.setTime(rs.getDate("dataEmprestimo"));
		emprestimo.setDataEmprestimo(data);
		

		if (rs.getDate("dataDevolucao") != null) {
			data.setTime(rs.getDate("dataDevolucao"));
			emprestimo.setDataDevolucao(data);
		}else {
			emprestimo.setDataDevolucao(null);
		}

		return emprestimo;
		    }

}


	
