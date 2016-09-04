//package myjava.homework.server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			int port = Integer.parseInt(args[2]);
			int client_number=1;
			int[] products = new int[2];
			products[0] = Integer.parseInt(args[0]);
			products[1] = Integer.parseInt(args[1]);
			
			ServerSocket server = new ServerSocket(port, 100);
			Socket connection;
			BufferedReader input;
			PrintStream output;
			
			System.out.println("Port:"+port+"\nResource:<A : "+products[0]+",B : "+products[1]+" >");
			System.out.println("Listening Now");
			while (true) {
				try{
				String receive_msg;
				String[] split_msg;
				connection = server.accept();
				input = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
				output =  new PrintStream (connection.getOutputStream(),false,"UTF-8");
				output.flush();
				System.out.print("[Client_"+client_number+"] : ");
				
				receive_msg = input.readLine();
				split_msg = receive_msg.split(",");
				if(Integer.parseInt(split_msg[0]) <= products[0] && Integer.parseInt(split_msg[1]) <= products[1]){
					int[] least = new int[2];
					least[0] = products[0] - Integer.parseInt(split_msg[0]);
					least[1] = products[1] - Integer.parseInt(split_msg[1]);
					System.out.println("ǐ坝珇 -> A : "+Integer.parseInt(split_msg[0])+",B : "+Integer.parseInt(split_msg[1]));
					System.out.println("                   坝珇计 : A : "+least[0]+",B : "+least[1]);
					
					least[0] = products[0] - least[0];
					least[1] = products[1] - least[1];
					System.out.println("干砯-> A : "+least[0]+",B : "+least[1]);
					System.out.println("                   坝珇计 : A : "+products[0]+",B : "+products[1]);
					output.println("Service finish");
					client_number++;
					output.flush();
					input.close();
					output.close();
				}else{
					System.out.println("Resource insufficient! Error... ");
					System.out.println("干砯-> A : 0,B : 0");
					System.out.println("                   坝珇计 : A : "+products[0]+",B : "+products[1]);
					output.println("[Error]:Resorce quantity insufficient");
					output.flush();
					client_number++;
					input.close();
					output.close();
				}
				}catch(IOException e){
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
