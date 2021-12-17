package Network;


public class Test_tcp_client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*TCP_Client client_connect = new TCP_Client() ;
		TCP_Client client_listen = new TCP_Client();
		TCP_Client client_send = new TCP_Client();*/
		
		try {
			TCP_Client.getInstance().connectToServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TCP_Client.getInstance().listenForMessages();
		
		while(true) {
			try {
				TCP_Client.getInstance().sendMessage("yoyo");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		

	}

}
