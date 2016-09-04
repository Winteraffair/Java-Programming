package myjava.homework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Keypad {
	
	private BufferedReader br;
	// initializes 
	public Keypad () {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/*
	 *  This function may throw some Exception if the user enters non-integer input.
	 *  You must use try...catch to deal with it.
	 */
	public int getInput() {
		/* Fill your code here */
		try {
			String msg_read = br.readLine();
			char[] check_int = msg_read.toCharArray();
			//check first char 0
			if(check_int[0]=='0'){
				return 0;
			}
			//check if digit
			for(int i=0;i<check_int.length;i++){
				if(!Character.isDigit(check_int[i])){
					return 0;
				}
			}
			
			return Integer.parseInt(msg_read);
		} catch (IOException e) {
			
			//System.out.println("Invalid range of number");
			return 0;
		}
		
	}
	
}
