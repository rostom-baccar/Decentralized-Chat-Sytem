package Tests;

import java.util.ArrayList;

public class ClassA {
	private static ArrayList<String> localClients = new ArrayList<>();
	
	public static void main(String[] argv) {
		localClients.add("Rostom");
		localClients.add("Wissem");
		
		for (int i=0; i<=localClients.size()-1;i++) {
			System.out.println("Local Client: "+localClients.get(i));
		}
		
		
	}
}
