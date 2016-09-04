package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static String get_typein(BufferedReader rd) {
		try {
			String type_in = rd.readLine();
			return type_in;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		Socket socket = null;
		Client_GUI gui_open;
		
		while (true) {
			try {
				socket = new Socket("127.0.0.1",12345);
				break;
			} catch (Exception ex) {
				System.out.println("connection failed");
			}

		}
		while(true){
			try {
				gui_open = new Client_GUI(socket);
				break;
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

}
