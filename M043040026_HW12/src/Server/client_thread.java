package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class client_thread implements Runnable {
	private Socket client;
	private QueueMachine queue;
	private ArrayList<Account> account_data;
	DataInputStream input;
	DataOutputStream output;
	Account account;
	public client_thread(Socket client, QueueMachine queue, ArrayList<Account> account_data) {
		this.client = client;
		this.queue = queue;
		this.account_data = account_data;
		
		try {
			this.input = new DataInputStream(client.getInputStream());
			this.output = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				output.writeUTF("OK");
				for(int i=0;i<5;i++){
					//name,amount,trantype
					String[] trans_information = input.readUTF().split(",");
					if(i==0){
						this.account_data.add(account = new Account(trans_information[0],5000));
					}
					queue.add(new Transaction(i+1,Integer.parseInt(trans_information[1]),trans_information[2],this.account));
					
				}
				input.close();
				output.close();
				client.close();
				break;
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
	}

}
