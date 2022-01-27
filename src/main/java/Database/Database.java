package Database;

import java.sql.* ;
import Interface.ChatWindow;

public class Database {
	


////  INSA Database
	private String dbaddr="jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_018";
	private String username="tp_servlet_018";
	private String mdp = "izu6uNgu";
	

//	Attributs
	private static Connection con;
	private static Statement stmt ;
	
	
	////// QUERIES \\\\\\
//	To create the Table
	String sqlCreateTab = "CREATE TABLE IF NOT EXISTS msg_history"
			+ "  (id			INT NOT NULL AUTO_INCREMENT,"
			+ "   sender           VARCHAR(45) NOT NULL,"
			+ "   receiver            VARCHAR(45) NOT NULL,"
			+ "   message          VARCHAR(200) NULL,"
			+ "   tstamp           DATETIME NULL,"
			+ "   PRIMARY KEY (id)) " ;
				
//	To insert a row into the table
	String sqlInsert = "Insert into msg_history (sender, receiver,message,tstamp) values (?,?,?,?)" ;
	
//	To Drop the table
	String sqlDrop = "DROP TABLE msg_history" ;

//	To get chat History
	String getChatHistory = "SELECT sender, receiver, message, tstamp " 
			  + "FROM msg_history "
			  + "WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?)" 
			  + "ORDER BY tstamp ASC Limit ?";
	

	public Database () {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(dbaddr,username,mdp);
			stmt = con.createStatement();
			System.out.println("Connected to Database. \n");
			
			stmt.execute(sqlCreateTab);
			System.out.println("Table created. \n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int insertRow(String sender, String receiver, String message, String timestamp) {
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlInsert);
			pstmt.setString(1, sender);
			pstmt.setString(2, receiver);
			pstmt.setString(3, message);
			pstmt.setString(4, timestamp);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void LoadChatHistory (Database clientdb, ChatWindow chatWindow, String LocalIpAddress, String RemoteIpAddress, String username, String remoteUser) {
		try {
			
			
			ResultSet rs = clientdb.getHistory(LocalIpAddress,RemoteIpAddress);
			while (rs.next()){
				if (rs.getString(1).equals(LocalIpAddress)) {
					String tstamp = rs.getString(4) ;
					chatWindow.getChatArea().append("                                                "+tstamp.substring(0,16)+"\n");
					chatWindow.getChatArea().append("["+username+"]: "+rs.getString(3)+"\n");
				}else {
					String tstamp = rs.getString(4) ;
					chatWindow.getChatArea().append("                                                "+tstamp.substring(0,16)+"\n");
					chatWindow.getChatArea().append("["+remoteUser+"]: "+rs.getString(3)+"\n");
				}
			}
		} catch (Exception ee) {
			System.out.print("Error while loading History ! \n");
			ee.printStackTrace();

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
			e.printStackTrace();
			return null;
		}

	}
	
	
	public void dropDatabase() {
		try {
			stmt.executeUpdate(sqlDrop) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
