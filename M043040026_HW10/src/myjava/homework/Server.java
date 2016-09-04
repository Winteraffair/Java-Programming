package myjava.homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {
	public static Connection getConnection(String user, String password) {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			String url = "jdbc:hsqldb:hsql://localhost/java_db";
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// System.out.println(e);
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

	public static ResultSet getsqldata(Statement stat, String sql) {
		
		try {
			ResultSet result;
			result = stat.executeQuery(sql);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket server = null;
		try {
			server = new ServerSocket(6666, 100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		Socket client;
		String user = "";
		String password = "";
		DataInputStream input = null;
		DataOutputStream output = null;
		Connection conn = null;
		String sql = "";
		Statement stat = null;
		ResultSet result = null;
		ResultSetMetaData metadata = null;
		int conn_set = 0;
		while (true) {

			System.out.println("Ready for accept connection");

			try {
				client = server.accept();

				System.out.println("Client has connected");
				input = new DataInputStream(client.getInputStream());
				output = new DataOutputStream(client.getOutputStream());
				output.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while (conn_set == 0) {
				try {
					output.writeUTF("HSQL Database account:");
					output.flush();
					user = getdata(input);
					output.writeUTF("HSQL Database password:");
					output.flush();
					password = getdata(input);
					conn = getConnection(user, password);
					if (conn == null) {
						output.writeUTF("Connection faild");
					} else {
						output.writeUTF("Connection to database sucess.");
						System.out.println("Connection to database sucess");
						stat = conn.createStatement();
						conn_set = 1;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Connection faild");
				}
			}
			while (true) {
				try {
					output.writeUTF("Enter your SQL Query:");
					output.flush();
					sql = input.readUTF();
					System.out.println("[SQL query] : " + sql);
					if (sql.equals("end")) {
						break;
					}

					result = getsqldata(stat, sql);
					if (result == null) {
						output.writeUTF("Invalid SQL query");
					} else {
						metadata = result.getMetaData();
						output.writeUTF("send_result_start");
						output.flush();
						while (result.next()) {
							String write_to_output = "";
							for (int i = 1; i <= metadata.getColumnCount(); i++) {
								if (i == 1)
									write_to_output = result.getString(metadata.getColumnName(i));
								else
									write_to_output = write_to_output + ","
											+ result.getString(metadata.getColumnName(i));
							}
							output.writeUTF(write_to_output);
							output.flush();
						}
						output.writeUTF("send_result_stop");
						output.flush();
					}
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				input.close();
				output.close();
				stat.close();
				conn.close();
				conn = null;
				conn_set = 0;
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("4");
			}

		}

	}

}
