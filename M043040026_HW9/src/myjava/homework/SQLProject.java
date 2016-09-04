package myjava.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class SQLProject {
	static String[] sql = { "SELECT id,birthmonth FROM students WHERE sex = 'M' ORDER BY birthmonth ASC",
			"SELECT id,math,chinese FROM grades WHERE math>90 AND chinese>80",
			"SELECT max(chinese) as c_max,min(math) as m_min,avg(english) as e_avg FROM grades",
			"SELECT st.id,sex,english FROM students as st,grades as gr WHERE st.id = gr.id AND gr.english > 50 GROUP BY st.id ORDER BY english DESC,sex ASC",
			"SELECT id,(math+chinese+english)/3 as grade_avg FROM grades",
			"SELECT birthmonth,count(birthmonth) as count FROM students GROUP BY birthmonth HAVING count(birthmonth) > 1 ORDER BY birthmonth ASC" };


	public static Connection getConnection(String user, String password) {
		try {
			String url = "jdbc:mysql://140.117.171.156/java_db?useUnicode=true&characterEncoding=Big5&useSSL=false";

			Connection conn = DriverManager.getConnection(url, user, password);
			Class.forName("com.mysql.jdbc.Driver");
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
	
				System.out.println("Can't connect to MySQL Server! Program has terminated !");
				System.exit(0);
			
		}
		return null;
	}
	public static String get_user_password(){
		BufferedReader rd_input = new BufferedReader(new InputStreamReader(System.in));
		
		String get_line=null;
		try {
			get_line = rd_input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return get_line;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//get user,password
			System.out.println("Please enter useraccount:");
			String user = get_user_password();
			System.out.println("Please enter userpassword:");
			String password = get_user_password();
			
			//establish connection
			Connection conn = getConnection(user, password);
		
			System.out.println("Connection establish\n");
			
			//start sending SQL query
			Statement stat = conn.createStatement();
			ResultSet result;
			ResultSetMetaData result_organization;
			int count = 1;//use to print "query %d result"
			for (String s : sql) {
				result = stat.executeQuery(s);
				result_organization = result.getMetaData(); // use to get column_count,column_name 
				System.out.printf("Query %d result:\n\n",count);
				switch (result_organization.getColumnCount()) {
				case 2:
					String[] column_name2 = { result_organization.getColumnName(1),
							result_organization.getColumnName(2)};
					System.out.printf("%15s%15s\n\n", column_name2[0], column_name2[1]);
					while (result.next()) {
						System.out.printf("%15s%15s\n", result.getString(column_name2[0]),
								result.getString(column_name2[1]));
					}
					break;
				case 3:
					String[] column_name3 = { result_organization.getColumnName(1),
							result_organization.getColumnName(2), result_organization.getColumnName(3) };
					System.out.printf("%15s%15s%15s\n\n", column_name3[0], column_name3[1], column_name3[2]);
					while (result.next()) {
						System.out.printf("%15s%15s%15s\n", result.getString(column_name3[0]),
								result.getString(column_name3[1]), result.getString(column_name3[2]));
					}
					break;
				default:
					System.out.println("Somethin Wrong");
					break;
				}
				System.out.println();
				count++;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
