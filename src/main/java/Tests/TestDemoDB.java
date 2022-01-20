package Tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDemoDB {

	public static void main(String[] args) {
		//String name = "wissem";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","root");
			Statement stmt = con.createStatement();
			
			
			
			String sqlCreate = "CREATE TABLE IF NOT EXISTS POKECARD"
					+ "  (brand           VARCHAR(10),"
					+ "   year            INTEGER,"
					+ "   number          INTEGER,"
					+ "   value           INTEGER,"
					+ "   card_count           INTEGER,"
					+ "   player_name     VARCHAR(50),"
					+ "   player_position VARCHAR(20))";
			
			int num = stmt.executeUpdate(sqlCreate);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}


}
