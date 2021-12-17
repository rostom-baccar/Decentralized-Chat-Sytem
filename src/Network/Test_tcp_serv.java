package Network;


public class Test_tcp_serv {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*TCP_Server serv_connect = new TCP_Server() ;
		TCP_Server serv_listen = new TCP_Server() ;
		TCP_Server serv_send = new TCP_Server() ;*/
		
		try {
			TCP_Server.getInstance().startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TCP_Server.getInstance().listenForMessages();
		
		try {
			TCP_Server.getInstance().sendMessage("yooo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
