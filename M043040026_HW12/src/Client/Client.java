package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Client {
	private static Socket getServer_conn() {
		try {
			Socket server = new Socket("127.0.0.1", 64026);
			return server;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static int getNum() {
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
			int number = Integer.parseInt(rd.readLine());
			return number;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// connect to server && get input,output
		Socket server;
		Executor thread_pool = Executors.newCachedThreadPool();
		int run_once = 0;
		// get input and create customers
		while (run_once == 0) {
			System.out.println("How many Customer do you want ?");
			int customer_num = getNum();
			if (customer_num == 0) {
				System.out.println("Your Input has some problems , Please type a Number !");
			} else {
				run_once = 1;
				for (int i = 0; i < customer_num; i++) {
					if ((server = getServer_conn()) == null) {
						System.out.println("Connecting Server Error , Program stop !");
						System.exit(0);
					}
					customer_thread newCustomer = new customer_thread(server, i + 1);
					thread_pool.execute(newCustomer);
				}
			}
		}

	}

}
