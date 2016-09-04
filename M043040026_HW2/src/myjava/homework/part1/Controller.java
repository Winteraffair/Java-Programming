package myjava.homework.part1;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int action, score, location, list_size, pass, fail;
		String id, name;

		StudentInformation test;
		ArrayList<StudentInformation> student_list = new ArrayList<StudentInformation>();

		while (true) {
			try {
				System.out.println("Type 1: add a student's data(Studen ID,Studen Name,Score)");
				System.out.println("Type 2: show student's data");
				System.out.println("Type 3: show all student's data");
				System.out.println("Type 4: exit the program");
				System.out.print("Type a number:");

				action = Integer.parseInt(scanner.nextLine().toString());

				switch (action) {
				case 1:
					System.out.println("Add a new student data");
					System.out.print("Student ID:");
					id = scanner.nextLine().toString();
					System.out.print("Student Name:");
					name = scanner.nextLine().toString();
					System.out.print("Score:");
					score = Integer.parseInt(scanner.nextLine().toString());

					student_list.add(test = new StudentInformation(id, name, score));
					location = student_list.indexOf(test);
					System.out.printf("This is student %d\n", location + 1);
					break;
				case 2:
					System.out.print("To show which student information :");
					location = Integer.parseInt(scanner.nextLine().toString());
					list_size = student_list.size();
					if (list_size < location) {
						System.out.println("\nDate not find\n");
					} else {
						test = student_list.get(location - 1);
						System.out.println("");
						test.show_data();
						System.out.println("");
					}
					break;
				case 3:
					list_size = student_list.size();
					pass = 0;
					fail = 0;
					System.out.println("====Student's Date====");
					for (int i = 0; i < list_size; i++) {
						test = student_list.get(i);
						if (test.getScore() >= 70) {
							pass++;
						} else {
							fail++;
						}
						test.show_data();
						System.out.println("");
					}
					System.out.println("======================");
					System.out.printf("Pass : %d\nNo Pass: %d\n", pass, fail);
					System.out.println("======================");
					break;
				case 4:
					System.out.println("Bye !");
					scanner.close();
					System.exit(0);
					break;
				default:
					System.out.println("Wrong number !");
					break;
				}
			} catch (Exception ex) {
				System.out.println("Something Wrong !");
			}
		}

	}

}
