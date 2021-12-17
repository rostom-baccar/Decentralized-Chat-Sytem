package Network;

import java.io.*;
import java.net.Socket;


//import intf.MessageInterface;

public class TCP_Client {
	
	private static TCP_Client instance;
	private Socket socketConn;
    private InputStreamReader isr;
    private OutputStreamWriter osw;
    
    public static TCP_Client getInstance(){
        if(instance == null){
            instance = new TCP_Client();
        }
        return instance;
    }
    
    public void connectToServer(/*MessageInterface mi*/) throws Exception{
        //this.mi = mi;
        System.out.println("Connection to server...");
        socketConn = new Socket("127.0.0.1", 3535);
        isr = new InputStreamReader(socketConn.getInputStream());
        osw = new OutputStreamWriter(socketConn.getOutputStream());
        System.out.println("Conneted to server");
        listenForMessages();
    }
    
    public void listenForMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        char[] charMessage = new char[1024];
                        if(isr != null){
                            String message = new String(charMessage);
                            //mi.onMessageReceived(message);
                            System.out.println(message);
                        }
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
            }
        }).start();
    }
    
    public void sendMessage(String message)throws Exception{
        osw.write(message);
        osw.flush();
    }
    

}
