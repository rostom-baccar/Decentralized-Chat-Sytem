package Tests;

public class TestThreadTCP {
	
	public static void main (String arg[]) {
		TCP_client t1;
		TCP_server t2;
		//t1 = new TCP_client("sarra","10.1.5.148");
		t1 = new TCP_client();
		t2 = new TCP_server();
		//t1.start();
		//t2.start();
		
	}
}
 