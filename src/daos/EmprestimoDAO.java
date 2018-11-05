package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Emprestimo;

public class EmprestimoDAO {
	private Connection connection;

	public EmprestimoDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public boolean inserir( Emprestimo emprestimo) {

		String sql = "insert into emprestimo (matricula, titulo, dataEmprestimo, dataDevolucao) values (?, ?, ?, ?);";
	}
	try 

