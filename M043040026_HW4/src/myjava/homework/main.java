package myjava.homework;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Scanner scanner = new Scanner(System.in);
				ArrayList<Integer> usernums, winnums;
				Permutation permutation_object;
				Combination combination_object;
				int input_num;

				System.out.println("Choose your Four Star Game type : (1:Permutation 2:Combination 3:Exit)");
				input_num = Integer.parseInt(scanner.nextLine().toString());
				switch (input_num) {
				case 1:
					permutation_object = new Permutation();
					permutation_object.generateWinNums();
					winnums = permutation_object.getWinNums();
					permutation_object.generateUserNums();
					permutation_object.checkOfWin();
					break;
				case 2:
					combination_object = new Combination();
					combination_object.generateWinNums();
					winnums = combination_object.getWinNums();
					combination_object.generateUserNums();
					combination_object.checkOfWin();
					break;
				case 3:
					System.out.println("Bye");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("Wrong input");
					break;
				}

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

}
