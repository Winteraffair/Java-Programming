package myjava.homework;

import java.util.Scanner;
import java.lang.Math;

public class HW1_prime {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n, leave = 0;
		int numofprime = 0, change_line = 0;
		Scanner scanner = new Scanner(System.in);
		while (leave == 0) {
			System.out.print("1.Check whether it's prime number\n" + "2.Fine prime numbers(2~N)\n" + "3.Leave\n"
					+ "Please type 1~3 to choose: ");
			n = scanner.nextInt();
			switch (n) {
			case 1:
				System.out.print("Input the number:\n");
				n = scanner.nextInt();
				if (n <= 1) {
					System.out.print("Input error : N must equal greater than 2\n");
				}
				// check prime number
				numofprime = 0;
				for (int i = 2; i <= Math.floor(Math.sqrt(n)); i++) {
					if (n % i == 0) {
						numofprime++;
						System.out.printf("%d is not a prime number\n", n);
						break;
					}
				}
				if (numofprime == 0) {
					System.out.printf("%d is a prime number\n", n);
				}
				break;
			case 2:
				System.out.print("Input the number:");
				n = scanner.nextInt();
				if (n <= 1) {
					System.out.print("Input error : N must equal greater than 2\n");
				}
				// list prime number
				for (int i = 2; i <= n; i++) {
					numofprime = 0;
					for (int j = 2; j <= Math.floor(Math.sqrt(i)); j++) {
						if (i % j == 0) {
							numofprime++;
						}
					}
					if (numofprime == 0) {
						change_line++;
						System.out.printf("%d\t", i);
						if (change_line == 10) {
							System.out.printf("\n");
							change_line = 0;
						}
					}
				}
				System.out.printf("\n");
				break;
			case 3:
				scanner.close();
				System.out.print("Bye");
				System.exit(0);
			default:
				System.out.print("Wrong number , Please type again\n");
				break;
			}
		}

	}

}
