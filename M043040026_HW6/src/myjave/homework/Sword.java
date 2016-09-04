package myjave.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Sword extends Human implements Skill {
	
	public Sword() {
		setHP(100);
		setAttack(30);
	}

	@Override
	public int action() {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = 1;
		try {
			System.out.print("Choose(Default Attack)(1)Attack(2)defend(3)Attack*2(4)Powerful Attack:");
			choice = Integer.parseInt(br.readLine());
			switch(choice){
			case 1:
				return 1;
			case 2:
				return 2;
			case 3:
				this.skill_act();
				return 3;
			case 4:
				this.skill_act1();
				return 4;
			default:
				return 1;
			}
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//System.out.println("Wrong Input");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("Wrong Input");
		}
		return 1;
	}

	@Override
	public int skill_act() {
		// TODO Auto-generated method stub
		setAttack(getAttack()*2);
		System.out.println("Now,your damage:" + getAttack());
		return 0;
	}

	@Override
	public int skill_act1() {
		// TODO Auto-generated method stub
		setHP(1);
		return 0;
		
	}

}
