package Tests;
import java.util.Scanner;

class ThreadA extends Thread{
	String msg;
	public void run() {
		synchronized(this) {
			while(true) {
				Scanner s = new Scanner(System.in);
				msg = s.nextLine();
				if (msg.equals("rostom")) {
					this.notify();
					while (true) {}
				}}}}}

public class ClassA {

	public static void main(String[] args) throws InterruptedException {
		ThreadA a = new ThreadA();
		a.start();
		//Thread.sleep(1000);		
		synchronized(a) {
			System.out.println("Main Thread waiting");
			a.wait();
			System.out.println("Main Thread got notified: "+a.msg);}}
	}
