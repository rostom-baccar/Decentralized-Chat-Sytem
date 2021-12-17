package Network;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


//import intf.MessageInterface;



public class TCP_Server {
	
	public static TCP_Server instance;
    private ServerSocket serverSock;
    private Socket socket;
    private InputStreamReader isr;
    private OutputStreamWriter osw;
    
    
    public static TCP_Server getInstance(){
        if(instance == null){
            instance = new TCP_Server();
        }
        return instance;
    }
    
    public void startServer(/*MessageInterface mi*/) throws Exception{
        //this.mi = mi;
        serverSock = new ServerSocket(3535);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        System.out.println("Server online...");
                        socket = serverSock.accept();
                        isr = new InputStreamReader(socket.getInputStream());
                        osw = new OutputStreamWriter(socket.getOutputStream());
                        System.out.println("Client Server Connection OK");
                        listenForMessages();
                        break;
                    }catch (Exception e){
                        System.err.println("Server Listening Error!");
                    }
                }
            }
        }).start();
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
