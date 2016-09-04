//package majava.homework.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			String server_ip = args[2];
			int port = Integer.parseInt(args[3]);
			int[] requirement = new int[2];
			requirement[0] = Integer.parseInt(args[0]);
			requirement[1] = Integer.parseInt(args[1]);
			if(requirement[0] < 0 || requirement[1] < 0){
				System.out.println("Incorrect quantity of products ");
				System.exit(0);
			}
			
			System.out.println("IP : "+server_ip+" Port : "+port);
			System.out.println("Resource requirement : < A : "+requirement[0]+",B : "+requirement[1]+" >");
			System.out.println("Connecting...\n");
			
			Socket socket = new Socket(server_ip,port);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintStream output =  new PrintStream (socket.getOutputStream(),false,"UTF-8");
			output.flush();
			String writing =  requirement[0]+","+requirement[1];
			output.println(writing);
			output.flush();
			String receive_msg = input.readLine();
			
			System.out.println(receive_msg);
			
			input.close();
			output.close();
			socket.close();
				
			
			
		}catch(Exception ex){
			System.out.println("Socket連線有問題!");
			System.out.println(ex);
		}
	}

}
