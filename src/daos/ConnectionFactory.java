
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection con = null;
<<<<<<< HEAD
	private static final String banco = "bibliotecas";
=======
<<<<<<< HEAD
	private static final String banco = "biblioteca";
=======
	private static final String banco = "biblioteca_lamary";
>>>>>>> branch 'master' of https://github.com/DudsF/BibliotecaS.git
>>>>>>> branch 'master' of https://github.com/DudsF/BibliotecaS.git
	private static final String user = "root";
	private static final String password = "lab02";

	public static Connection getConnection() {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost/" + banco, user, password);

			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
	
}
