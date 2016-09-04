package myjave.homework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Human player;
		Random random = new Random();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// TODO Auto-generated method stub
		while (true) {
			try {
				int character_choose = 1;
				int monster;
				monster = random.nextInt(151) + 150;
				System.out.println("Monster's HP: " + monster);
				System.out.print("Choose your character(By default Sword)(1)Sword(2)Magician:");
				character_choose = Integer.parseInt(br.readLine());
				switch (character_choose) {
				case 1:
					player = new Sword();
					fight(player, monster, br, random,1);
					break;
				case 2:
					player = new Magician();
					fight(player, monster, br, random,2);
					break;
				default:
					player = new Sword();
					fight(player, monster, br, random,1);
					break;
				}
			} catch (Exception ex) {
				System.out.println("Wrong Input");
			}
		}
	}

	public static void fight(Human Player, int monster, BufferedReader br, Random random,int career) {
		int fight_result, attack_ability = 1, player_damage, monster_damage;
		while (true) {
			try {
				System.out.println("Player's HP:" + Player.getHp());
				while (Player.getHp() != 0 && monster != 0) {
					monster_damage = random.nextInt(41);
					fight_result = Player.action();
					switch (fight_result) {
					case 1:
						if (attack_ability == 1) {
							player_damage = Player.getAttack();
							if (player_damage > monster) {
								monster = 0;
							} else {
								monster = monster - player_damage;
							}

							if (monster_damage > Player.getHp()) {
								Player.setHP(0);
							} else {
								Player.setHP(Player.getHp() - monster_damage);
							}
							System.out.println("Player's damage:" + player_damage);
							System.out.println("moster's damage:" + monster_damage);
							System.out.println("Player's HP:" + Player.getHp());
							System.out.println("monster's HP:" + monster);
						} else {
							if (monster_damage > Player.getHp()) {
								Player.setHP(0);
							} else {
								Player.setHP(Player.getHp() - monster_damage);
							}
							System.out.println("Player can't attack now");
							System.out.println("moster's damage:" + monster_damage);
							System.out.println("Player's HP:" + Player.getHp());
							System.out.println("monster's HP:" + monster);
						}
						if(career == 2){
							Player.setAttack(20);
						}
						break;
					case 2:
						System.out.println("defend all damage");
						System.out.println("moster's damage:" + monster_damage);
						System.out.println("Player's HP:" + Player.getHp());
						System.out.println("monster's HP:" + monster);
						break;
					case 3:
						if (monster_damage > Player.getHp()) {
							Player.setHP(0);
						} else {
							Player.setHP(Player.getHp() - monster_damage);
						}
						
						System.out.println("moster's damage:" + monster_damage);
						System.out.println("Player's HP:" + Player.getHp());
						System.out.println("monster's HP:" + monster);
						break;
					case 4:
						if (attack_ability == 1) {
							attack_ability = 0;
							player_damage = random.nextInt(351) + 100;
							if (player_damage > monster) {
								monster = 0;
							} else {
								monster = monster - player_damage;
							}

							/*if (monster_damage > Player.getHp()) {
								Player.setHP(0);
							} else {
								Player.setHP(Player.getHp() - monster_damage);
							}*/
							System.out.println("Power!!Player's damage:" + player_damage);
							System.out.println("moster's damage:" + monster_damage);
							System.out.println("Player's HP:" + Player.getHp());
							System.out.println("monster's HP:" + monster);
						} else {
							if (monster_damage > Player.getHp()) {
								Player.setHP(0);
							} else {
								Player.setHP(Player.getHp() - monster_damage);
							}
							System.out.println("Player can't attack now");
							System.out.println("moster's damage:" + monster_damage);
							System.out.println("Player's HP:" + Player.getHp());
							System.out.println("monster's HP:" + monster);
						}
						break;
					default:
						if (attack_ability == 1) {
							player_damage = Player.getAttack();
							if (player_damage > monster) {
								monster = 0;
							} else {
								monster = monster - player_damage;
							}

							if (monster_damage > Player.getHp()) {
								Player.setHP(0);
							} else {
								Player.setHP(Player.getHp() - monster_damage);
							}
							System.out.println("Player's damage:" + player_damage);
							System.out.println("moster's damage:" + monster_damage);
							System.out.println("Player's HP:" + Player.getHp());
							System.out.println("monster's HP:" + monster);
						} else {
							System.out.println("You can't attack now");
						}
						break;
					}
				}
				if (Player.getHp() == 0 && monster == 0) {
					System.out.println("Deuce\n");
					return;
				} else if (Player.getHp() != 0 && monster == 0) {
					System.out.println("Win\n");
					return;
				} else if (Player.getHp() == 0 && monster != 0) {
					System.out.println("Lose\n");
					return;
				}

			} catch (Exception ex) {
				System.out.println("Wrong Input");
			}
		}
	}

}
