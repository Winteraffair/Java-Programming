package myjava.homework.part2;

import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int action;
		String name;
		ItemList itemlist = new ItemList();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			try {
				System.out.println("Type 1: add item to list");
				System.out.println("Type 2: remove item from list");
				System.out.println("Type 3: show the list");
				System.out.println("Type 4: exit the program");
				System.out.print("Type a number:");

				action = Integer.parseInt(scanner.nextLine().toString());

				switch (action) {
				case 1:
					System.out.print("Add item name :");
					name = scanner.nextLine();
					itemlist.addItem(name);
					break;
				case 2:
					System.out.print("remove item name :");
					name = scanner.nextLine();
					itemlist.remove(name);
					break;
				case 3:
					System.out.println("show the list :");
					itemlist.printList();
					break;
				case 4:
					System.out.println("Bye !");
					scanner.close();
					System.exit(0);
					break;
				default:
					System.out.println("Wrong Number !");
					break;
				}

			} catch (Exception ex) {
				System.out.println("Something Wrong !");
			}
		}

	}

}
