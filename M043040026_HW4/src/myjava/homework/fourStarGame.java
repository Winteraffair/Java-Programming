package myjava.homework;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class fourStarGame {
	private ArrayList<Integer> userNums, winNums;

	public fourStarGame() {
		this.userNums = new ArrayList<Integer>();
		this.winNums = new ArrayList<Integer>();
	}

	public void generateUserNums() {
		try {
			Scanner scanner= new Scanner(System.in);
			Scanner scan_input;
			int input_num,Wrong_once;
			String input;
			while (true) {		
				Wrong_once = 0;
				System.out.print("Input four user numbers(like:5 3 1 0):");
				input = scanner.nextLine();
				scan_input = new Scanner(input);
				while(scan_input.hasNextInt()){
					input_num = scan_input.nextInt();
					if(input_num < 10){
						this.userNums.add(input_num);
					}
					else{
						System.out.println("Wrong input, try again");
						Wrong_once =1;
						break;
					}
				}
				
				if(this.userNums.size() == 4){
					//scanner.close();
					scan_input.close();
					return;
				}
				else if(Wrong_once == 0){
					this.userNums.clear();
					System.out.println("Wrong input, try again");
				}
				
			}
		} catch (Exception ex) {
			System.out.println("Something Wrong");
		}
	}

	public void generateWinNums() {
		Random random = new Random();
		int random_number = 0;

		for (int i = 0; i < 4; i++) {
			while (true) {
				random_number = random.nextInt(10);
				if (this.winNums.indexOf(random_number) == -1) {
					this.winNums.add(random_number);
					break;
				}
			}
		}
		System.out.printf("Win numbers:");
		for (int i = 0; i < 4; i++) {
			System.out.print(this.winNums.get(i) + " ");
		}
		System.out.println();

	}

	public ArrayList<Integer> getUserNums() {
		return this.userNums;
	}

	public ArrayList<Integer> getWinNums() {
		return this.winNums;
	}
}
