package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class client_thread implements Runnable {
	public static ArrayList<thread_controll> client_list = new ArrayList<thread_controll>();
	private Socket client;
	private String name;
	private thread_controll self_data;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	private DataOutputStream tmp_output = null;
	private Statement stat;
	private ResultSet result;
	private String json_start = "{\"Data\":{\"message\":\"", json_end = "\"}}";
	private String json_msg;
	private JSONObject json_object;
	private Date time_now;
	private SimpleDateFormat formatter = new SimpleDateFormat("[yyyy/MM/dd hh:mm:ss] ");
	
	

	public client_thread(Socket client, Statement stat, int mode) {
		try {
			if (mode == 0) {
				return;
			}
			this.stat = stat;
			this.client = client;
			this.input = new DataInputStream(this.client.getInputStream());
			this.output = new DataOutputStream(this.client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// print out client socket information
		System.out.println(client + " : come in");
		for (int i = 0; i < client_list.size(); i++) {
			System.out.println(client_list.get(i).name + " : " + client_list.get(i).client);
		}
		// get user name
		while (true) {
			try {
				String get_name = getdata();
				if (get_name == null) {
					output.writeUTF("Name Set failed");
				} else {
					//get client name
					this.name = get_name;
					output.writeUTF("Name Set Complete");
					this.self_data = new thread_controll(this.client, this.name);
					client_list.add(self_data);
					
					
					//send history msg to client
					result = stat.executeQuery("select * from history");
					this.json_msg = this.json_start + "-------History Start-------" + this.json_end;
					output.writeUTF(this.json_msg);
					while (result.next()) {
						this.json_msg = this.json_start  + result.getString(1) + result.getString(2) + " : "
								+ result.getString(3) + this.json_end;
						//System.out.println(this.json_msg);
						output.writeUTF(this.json_msg);
					}
					this.json_msg = this.json_start + "-------History End-------" + this.json_end;
					output.writeUTF(this.json_msg);
					
					//send log in msg to all , time+name+msg
					System.out.println(this.name + " log in");
					time_now = new Date();
					this.json_msg = this.json_start +formatter.format(time_now)+"Server : "+this.name+" in !" + this.json_end;
					insert_history(formatter.format(time_now),"Server",this.name+" in !");
					//send to myself too
					for (int i = 0; i < client_list.size(); i++) {
							this.tmp_output = new DataOutputStream(client_list.get(i).client.getOutputStream());
							this.tmp_output.writeUTF(this.json_msg);		
					}
					break;
				}
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(client +" : disconnect(not set name yet)");
				return;
			}
		}
		// start transmit client msg
		while (true) {
			try {
				//read msg
				this.json_object = new JSONObject(input.readUTF());
				//check if msg is bye 
				if ((this.json_object.getJSONObject("Data").getString("message")).equals("bye")) {
					System.out.println(this.name + " log out");
					time_now = new Date();
					this.json_msg = this.json_start +formatter.format(time_now)+"Server : "+this.name+" out !" + this.json_end;
					insert_history(formatter.format(time_now),"Server",this.name+" out !");
					//don't send to self , it will end quickly
					for (int i = 0; i < client_list.size(); i++) {
						if (!(client_list.get(i).client.equals(client))) {
							this.tmp_output = new DataOutputStream(client_list.get(i).client.getOutputStream());
							this.tmp_output.writeUTF(this.json_msg);
						}
					}
					client_list.remove(this.self_data);
					break;
				} else {
					System.out.println(this.name +" : "+ this.json_object.getJSONObject("Data").getString("message"));
					time_now = new Date();
					this.json_msg = this.json_start + formatter.format(time_now)+this.name+" : "+this.json_object.getJSONObject("Data").getString("message") + this.json_end;
					insert_history(formatter.format(time_now),this.name,this.json_object.getJSONObject("Data").getString("message"));
					for (int i = 0; i < client_list.size(); i++) {
						//if (!(client_list.get(i).client.equals(client))) {
							this.tmp_output = new DataOutputStream(client_list.get(i).client.getOutputStream());
							this.tmp_output.writeUTF(this.json_msg);
						//}
					}
				}
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(client +" : disconnect(don't use bye)");
				client_list.remove(this.self_data);
				return;
			}
		}
	}
	public void insert_history(String time,String name,String message){
		try {
			String insert_sql = "insert into history values(\""+time+"\",\""+name+"\",\""+message+"\")";
			this.stat.executeUpdate(insert_sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getdata() {
		String getline = null;
		try {
			getline = this.input.readUTF();
			for (int i = 0; i < client_list.size(); i++) {
				if ((client_list.get(i).name).equals(getline)) {
					return null;
				}
			}
			return getline;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return getline;
	}
}
