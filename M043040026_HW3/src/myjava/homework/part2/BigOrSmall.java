package myjava.homework.part2;

import java.util.Scanner;

public class BigOrSmall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			try {
				String game_style;
				Scanner scanner = new Scanner(System.in);
				System.out.println("Which game you want?(You can input big or small to play, or input exit to quit.)");
				game_style = scanner.nextLine();
				Race game;
				switch (game_style) {
				case "big":
					game = new Race(new RaceKind().BIG);
					game.start();
					break;
				case "small":
					game = new Race(new RaceKind().SMALL);
					game.start();
					break;
				case "exit":
					System.out.println("Game Over!!");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("You have error input. The game is failed");
					break;
				}

			} catch (Exception ex) {
				System.out.println("Something Wrong !");
			}
		}
	}

}
