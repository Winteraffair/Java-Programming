package myjava.homework.part2;

import java.util.Random;

public class Race {
	private int game_style;
	/*
	 * enum suits{ Club,Heart,Diamond,Spade }
	 */
	String suits[];
	String ranks[];
	String card[];

	public Race(int game_style) {
		this.game_style = game_style;
		this.suits = new String[] { "Spade", "Diamond", "Heart", "Club" };
		this.ranks = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		this.card = new String[52];
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				this.card[4*i+j] = "_" + this.suits[j] + "_" + this.ranks[i];
			}
		}
	}

	public void start() {
		Random random = new Random();
		int card_you = random.nextInt(52);
		int card_com;
		do {
			card_com = random.nextInt(52);
		} while (card_com == card_you);
		RaceKind test = new RaceKind();

		System.out.println("Your hand is " + card[card_you]);
		System.out.println("Com's hand is " + card[card_com]);
		if (this.game_style == test.BIG) {
			if (card_you > card_com) {
				System.out.println(card[card_you] + " is bigger than " + card[card_com]);
				System.out.println("So, you win!");
			} else {
				System.out.println(card[card_you] + " is smaller than " + card[card_com]);
				System.out.println("So, Com win!");
			}
		} else if (this.game_style == test.SMALL) {
			if (card_you > card_com) {
				System.out.println(card[card_you] + " is bigger than " + card[card_com]);
				System.out.println("So, Com win!");
			} else {
				System.out.println(card[card_you] + " is smaller than " + card[card_com]);
				System.out.println("So, You win!");
			}
		}

	}
}
