package Database;

import java.sql.* ;

public class LocalDatabase {
	
	private String dbaddr="jdbc:mysql://localhost:3306/demodb";
	private String username="root";
	private String mdp = "root";

//  DB insa
//	private String dbaddr="jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_008?";
//	private String username="tp_servlet_008";
//	private String mdp = "ees7Lozu";
	
	private static Connection con;
	private static Statement stmt ;
	
	
	////// QUERIES \\\\\\
	String sqlCreateTab = "CREATE TABLE IF NOT EXISTS LOCALDB"
			+ "  (id			INT NOT NULL AUTO_INCREMENT,"
			+ "   sender           VARCHAR(45) NOT NULL,"
			+ "   receiver            VARCHAR(45) NOT NULL,"
			+ "   message          VARCHAR(200) NULL,"
			+ "   tstamp           DATETIME NULL,"
			+ "   PRIMARY KEY (id)) " ;
			
//	String sqlGetMsg = "SELECT message FROM localdb WHERE sender = ?" ;
	
	String sqlInsert = "Insert into localdb (sender, receiver,message,tstamp) values (?,?,?,?)" ;
	
	// For local use
//	String getChatHistory = "SELECT sender, receiver, message, tstamp " 
//						  + "FROM localdb "
//						  + "WHERE sender = ? OR receiver = ?" 
//						  + "ORDER BY tstamp ASC Limit ?";
	
	// For INSA DB
	String getChatHistory = "SELECT sender, receiver, message, tstamp " 
			  + "FROM localdb "
			  + "WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?)" 
			  + "ORDER BY tstamp ASC Limit ?";

	public LocalDatabase () {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con=DriverManager.getConnection(dbaddr,username,mdp);
			this.stmt = con.createStatement();
			System.out.println("Connected to Database. \n");
			
			stmt.execute(sqlCreateTab);
			System.out.println("Table created. \n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public int insertLine(String sender, String receiver, String message, String timestamp) {
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlInsert);
			pstmt.setString(1, sender);
			pstmt.setString(2, receiver);
			pstmt.setString(3, message);
			pstmt.setString(4, timestamp);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet getHistory(String LocalUserIP ,String RemoteUserIP) {
		try {
			PreparedStatement pstmt = con.prepareStatement(getChatHistory);
			pstmt.setString(1, LocalUserIP);
			pstmt.setString(2, RemoteUserIP);
			pstmt.setString(3, RemoteUserIP);
			pstmt.setString(4, LocalUserIP);
			pstmt.setInt(5, 50);
			return pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
