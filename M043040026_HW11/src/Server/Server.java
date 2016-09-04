package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
	public HashMap<String, String> client = new HashMap<String, String>();

	public static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:memory");

			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			// System.out.println("Can't connect to MySQL Server! Program has
			// terminated !");
			// System.exit(0);

		}
		return null;
	}

	public static String getdata(DataInputStream client) {
		String getline = null;
		try {
			getline = client.readUTF();
			return getline;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getline;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket server = null;
		Socket client;

		Connection conn = null;

		Statement stat = null;

		// server bind Socket on port 12345 , limit 10 client
		try {
			server = new ServerSocket(12345, 10);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// sqlite get connection and create memory table 'history'
		conn = getConnection();
		try {
			stat = conn.createStatement();
			stat.executeUpdate("drop table if exists history");
			stat.executeUpdate("create table history (time String,sender String,msg String)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Executor executor = Executors.newFixedThreadPool(10);
			@SuppressWarnings("unused")
			client_thread first_thread = new client_thread(null, stat, 0);
			while (true) {
				client = server.accept();
				client_thread open_thread = new client_thread(client, stat, 1);
				executor.execute(open_thread);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
