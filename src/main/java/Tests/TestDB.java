package Tests;

import java.sql.*;

public class TestDB {

	public static void main(String[] args) {
		//String name = "wissem";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatsystemdb","root","root");
			Statement stmt = con.createStatement();
			
			/*
			String query1 = "Insert into localdb values ('10.10.10.10',1,'Hello1',2022-01-01 00:00:01)" ;
			String query2 = "Insert into localdb values ('10.10.10.11',1,'Hello2',2022-01-02 00:00:01)" ;
			String query3 = "Insert into localdb values ('10.10.10.12',1,'Hello3',2022-01-03 00:00:01)" ;
			String query4 = "Insert into localdb values ('10.10.10.13',1,'Hello4',2022-01-04 00:00:01)" ;
			
			int num = stmt.executeUpdate(query1);
			num = stmt.executeUpdate(query2);
			num = stmt.executeUpdate(query3);
			num = stmt.executeUpdate(query4);
			*/
			
			String sql = "select * from localdb where Initiator = 1 ";
			ResultSet rs = stmt.executeQuery(sql);
		
			while (rs.next()){
				System.out.print("LocalUser Initiated Chat with :"+rs.getString(1)+"\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
