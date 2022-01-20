package Tests;

import java.sql.*;

public class TestDB {

	public static void main(String[] args) {
		//String name = "wissem";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","root");
			Statement stmt = con.createStatement();
			
			
			
			String query1 = "Insert into localdb (remoteUser, initiator,message,tstamp) values ('10.10.10.14',1,'Hello1','2022-01-01 00:00:01')" ;
			String query2 = "Insert into localdb (remoteUser, initiator,message,tstamp) values ('10.10.10.15',0,'Hello2','2022-01-02 00:00:01')" ;
			String query3 = "Insert into localdb (remoteUser, initiator,message,tstamp) values ('10.10.10.16',0,'Hello3','2022-01-03 00:00:01')" ;
			String query4 = "Insert into localdb (remoteUser, initiator,message,tstamp) values ('10.10.10.17',1,'Hello4','2022-01-04 00:00:01')" ;
			
			String query5 = "Insert into localdb (remoteUser, initiator,message,tstamp) values ('ipAddress',0,'Hello4','2022-01-04 00:00:01')" ;
			//int num = stmt.executeUpdate(query5);
			String getChatHistory = "SELECT remoteUser, initiator, message, tstamp " 
					  + "FROM localdb "
					  + "WHERE remoteUser = ? " 
					  + "ORDER BY tstamp DESC Limit ?";
			/*int num = stmt.executeUpdate(query1);
			num = stmt.executeUpdate(query2);
			num = stmt.executeUpdate(query3);
			num = stmt.executeUpdate(query4);
			
			
			String sql = "select * from localdb where Initiator = 1 ";
			ResultSet rs = stmt.executeQuery(sql);
		
			while (rs.next()){
				System.out.print("LocalUser Initiated Chat with :"+rs.getString(1)+"\n");
			}*/
			try {
				PreparedStatement pstmt = con.prepareStatement(getChatHistory);
				pstmt.setString(1, "ipAddress");
				pstmt.setInt(2, 50);
				ResultSet rs =pstmt.executeQuery();
				while (rs.next()){
					//System.out.print(rs.getInt(2)+rs.getString(3)+"\n");
					if (rs.getInt(2) == 1) {
						System.out.print("local :"+rs.getString(3)+"\n");
					}else {
						System.out.print("remote :"+rs.getString(3)+"\n");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
