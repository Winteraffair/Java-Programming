package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class customer_thread implements Runnable{
	private Socket server;
	private DataInputStream input;
	private DataOutputStream output;
	int name;
	public customer_thread(Socket server,int name){
		this.server = server;
		this.name = name;
		try {
			this.input = new DataInputStream(this.server.getInputStream());
			this.output = new DataOutputStream(this.server.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
		String[] trantype = {"withdraw","deposit"};
		int count=5000;
		//wait server to start thread
		try {
			input.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i=0;i<5;i++){
			try {
				int mode = random.nextInt(2);
				int amount = random.nextInt(200)+100;
				count = (mode == 0? count - amount:count + amount);
				this.output.writeUTF("Customer_"+this.name+","+amount+","+trantype[mode]);
				Thread.sleep(random.nextInt(200)+100);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Customer_"+name+" : "+count);
		
		try {
			input.close();
			output.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
