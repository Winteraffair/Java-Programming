package Server;

import java.net.Socket;

public class thread_controll {
	public Socket client;
	public String name;
	public thread_controll(Socket client,String name){
		this.client = client;
		this.name = name;
		
	}
}
