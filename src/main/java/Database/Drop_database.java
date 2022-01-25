package Database;

import java.sql.*;

public class Drop_database {

	public static void main(String[] args) {
		//String name = "wissem";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_018","tp_servlet_018","izu6uNgu");
			Statement stmt = con.createStatement();
			
			
			
			
			
			
			String sql = "select * from msg_history ";
			ResultSet rs = stmt.executeQuery(sql);
		
			while (rs.next()){
				System.out.print(rs.getString(1)+" : "+rs.getString(2)+" : "+rs.getString(3)+" : "+rs.getString(4)+" : "+rs.getString(5)+"."+"\n");
			}
//			String sqlDrop = "DROP TABLE msg_history" ;
//			stmt.executeUpdate(sqlDrop) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
