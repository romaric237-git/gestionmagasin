package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BdConnexion {

	private static String url = "jdbc:mysql://localhost:3306/neb_tontine";
	private static String user = "root";
	private static String passwd = "";

	private static Connection con;

	public static Connection getInstance() throws SQLException {

		con = DriverManager.getConnection(url, user, passwd);

		return con;
	}

	

}
