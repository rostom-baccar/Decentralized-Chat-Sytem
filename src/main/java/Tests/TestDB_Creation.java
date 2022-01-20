package Tests;

import java.sql.*;

public class TestDB_Creation {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = "jdbc:mysql://localhost";
		String username = "root";
		String password = "root";
		String sql = "CREATE DATABASE IF NOT EXISTS DEMODB";
		
		
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","root");
			Statement stmt = con.createStatement();
			
			
			String sqlCreateTab = "CREATE TABLE IF NOT EXISTS LOCALDB"
					+ "  (id			INT NOT NULL AUTO_INCREMENT,"
					+ "   remoteUser           VARCHAR(45) NOT NULL,"
					+ "   initiator            BOOLEAN NOT NULL,"
					+ "   message          VARCHAR(200) NULL,"
					+ "   Timestamp           DATETIME NULL,"
					+ "   PRIMARY KEY (id )) " ;
			/*
			String sqlCreate = "CREATE TABLE IF NOT EXISTS POKECARD"
					+ "  (brand           VARCHAR(10),"
					+ "   year            INTEGER,"
					+ "   number          INTEGER,"
					+ "   value           INTEGER,"
					+ "   card_count           INTEGER,"
					+ "   player_name     VARCHAR(50),"
					+ "   player_position VARCHAR(20))";
			*/
			int num = stmt.executeUpdate(sqlCreateTab);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}


