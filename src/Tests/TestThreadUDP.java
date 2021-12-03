package Tests;


public class TestThreadUDP {
	
	public static void main (String arg[]) {
		UDP_client t1;
		UDP_server t2;
		t1 = new UDP_client();
		t2 = new UDP_server();
		t2.start();
		t1.start();
	}
}
