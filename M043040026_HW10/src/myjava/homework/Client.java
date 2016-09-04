package myjava.homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

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
		DataInputStream input = null;
		DataOutputStream output = null;
		while (true) {
			try {
				System.out.println("Address:");
				String server_ip = get_typein(rd);
				System.out.println("Port:");
				int port = Integer.parseInt(get_typein(rd));
				socket = new Socket(server_ip, port);
				break;
			} catch (Exception ex) {
				System.out.println("connection failed");
			}

		}
		try {
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());

			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String conn_result = "";
		String database_account = "", database_pawssword = "";
		while (!conn_result.equals("Connection to database sucess.")) {
			try {
				// enter account
				conn_result = input.readUTF();
				System.out.println(conn_result);
				database_account = get_typein(rd);
				output.writeUTF(database_account);
				// enter password
				conn_result = input.readUTF();
				System.out.println(conn_result);
				database_pawssword = get_typein(rd);
				output.writeUTF(database_pawssword);
				// check connection
				conn_result = input.readUTF();
				System.out.println(conn_result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String sql_result = "", sql = "";
		String sql_data = "";
		ArrayList<String> write_string = new ArrayList<String>();
		while (true) {
			try {
				sql_result = input.readUTF();

				System.out.println(sql_result);
				sql = get_typein(rd);
				output.writeUTF(sql);
				if (sql.equals("end")) {
					break;
				}
				sql_result = input.readUTF();
				if (sql_result.equals("send_result_start")) {
					write_string.clear();
					while (true) {
						sql_data = input.readUTF();
						if (sql_data.equals("send_result_stop")) {
							break;
						}
						write_string.add(sql_data);
					}
				}else{
					System.out.println(sql_result);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedWriter wr;
		FileSystemView fv = FileSystemView.getFileSystemView();;
		try {
			String home_path = fv.getHomeDirectory().toString();
			wr = new BufferedWriter(new FileWriter(home_path+"/Result.csv"));

			for (String st : write_string) {
				wr.write(st);
				wr.newLine();
			}
			wr.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
