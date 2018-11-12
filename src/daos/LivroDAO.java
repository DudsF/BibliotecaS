package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Livro;

public class LivroDAO {
	
	private Connection connection;

	public LivroDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public boolean inserir( Livro livro) {

		String sql = "insert into livros (titulo, autor, editora, anoPub, edicao) values (?, ?, ?, ?, ?);";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getAutor());
			stmt.setString(3, livro.getEditora());
			
			stmt.setDate(4, new java.sql.Date(livro.getAnoPub().getTimeInMillis()));
			
			stmt.setString(5, livro.getEdicao());
	
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public List<Livro> buscar () {
		List<Livro> result = new ArrayList<>();
		String sql = "select * from livros;";

		try {
			PreparedStatement stmt = this.connection.prepareStatement (sql);
//			stmt.setString(1, livro);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Livro
				Livro livr = new Livro();
				livr.setId(rs.getLong("id"));
				livr.setTitulo(rs.getString("titulo"));
				livr.setAutor(rs.getString("autor"));
				livr.setEditora(rs.getString("editora"));
				livr.setEdicao(rs.getString("edicao"));

				// montando a data atrav�s do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("anoPub"));
				livr.setAnoPub(data);

				// adicionando o objeto � lista
				result.add(livr);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean alterar (Livro livro) {
		String sql = "update livros set titulo=?, autor=?, editora=? , anoPub=? edicao=? where id=?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getAutor());
			stmt.setString(3, livro.getEditora());
			stmt.setString(4, livro.getEdicao());
			stmt.setDate(4, new java.sql.Date(livro.getAnoPub().getTimeInMillis()));
			stmt.setLong(6,livro.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean remover (Livro livro) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from livros where id=?;");
			stmt.setLong(1, livro.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Livro getById(Long id) {
		Livro result = null;

		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from livros where id = ?;");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				// criando o objeto Livro
				result = new Livro();
				result.setId(rs.getLong("id"));
				result.setTitulo(rs.getString("titulo"));
				result.setAutor(rs.getString("autor"));
				result.setEditora(rs.getString("editora"));
				result.setEdicao(rs.getString("edicao"));

				// montando a data atrav�s do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("anoPub"));
				result.setAnoPub(data);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Livro getByTitulo(String titulo) {
		Livro result = null;

		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from livros where titulo = ?;");
			stmt.setString(1, titulo);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				result = new Livro();
				result.setId(rs.getLong("id"));
				result.setTitulo(rs.getString("titulo"));
				result.setAutor(rs.getString("autor"));
				result.setEditora(rs.getString("editora"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("anoPub"));
				result.setAnoPub(data);
				
				result.setEdicao(rs.getString("edicao"));
				
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
