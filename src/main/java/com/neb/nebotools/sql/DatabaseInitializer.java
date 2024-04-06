package com.neb.nebotools.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
	private static String DB_URL = "jdbc:mysql://localhost/";
	private static String USER = "root";
	private static String PASS = "";
	private static String dbName = "neb_tontine";


	public static void init() throws SQLException, ClassNotFoundException, IOException {
		Connection con = null;
		Statement stmt = null;

		try {

			con = DriverManager.getConnection(DB_URL, USER, PASS);
			if(databaseExists(con))
				System.out.println("La base de donnée existe");
			else
				createDatabase("/sql/bd.sql");
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (con != null) 	con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean databaseExists(Connection con) throws SQLException {
		boolean dbExists = false;
		ResultSet resultset = con.getMetaData().getCatalogs();
		while(resultset.next()) {
			String databaseName = resultset.getString(1);{
				if(databaseName.equals(dbName)) {
					dbExists = true;
					break;
				}
			}
		}
		resultset.close();
		return dbExists;
	}

	private static void createDatabase(String sqlFilePath) throws SQLException, IOException {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE DATABASE " + dbName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (
				Connection con = DriverManager.getConnection(DB_URL + dbName, USER, PASS)) { 
			Statement stmt = con.createStatement();

			StringBuilder sb = new StringBuilder();
			String line;
			BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/main/java/com/neb/nebotools/"+sqlFilePath)));

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			String[] sqls = sb.toString().split(";");

			for (String sql : sqls) {
				stmt.addBatch(sql);
			}

			try {
				stmt.executeBatch();
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void destroy() {
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
			if(databaseExists(con)) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("DROP DATABASE " + dbName);
			}
		} catch (SQLException f) {
			f.printStackTrace();
		}
	}

}
