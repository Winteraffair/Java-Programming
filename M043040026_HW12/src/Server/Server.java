package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

	private static int get_Num() {
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
			int number = Integer.parseInt(rd.readLine());
			return number;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	private static ServerSocket bind_address() {
		try {
			ServerSocket bind_address = new ServerSocket(64026);
			return bind_address;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Account> account_data = new ArrayList<Account>();
		ServerSocket server;
		Socket client;
		Executor thread_pool = Executors.newCachedThreadPool();
		QueueMachine queue = new QueueMachine();

		// bind address
		if ((server = bind_address()) == null) {
			System.out.println("Server Bind Error");
			System.exit(0);
		}

		// get clerk amount and create clerk
		while (true) {
			System.out.println("How many Clerk do you want ? ");
			int clerk = get_Num();
			if (clerk == 0) {
				System.out.println("Your Input has some problems , Please type a Number !");
			} else {
				for (int i = 0; i < clerk; i++) {
					thread_pool.execute(new Clerk(queue, "Clerk_" + (i + 1)));
					System.out.println("Clerk_" + (i + 1) + " is working Now");
				}
				break;
			}
		}

		// Starting accept client
		while (true) {
			try {
				client = server.accept();
				client_thread client_thread = new client_thread(client,queue,account_data);
				thread_pool.execute(client_thread);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
