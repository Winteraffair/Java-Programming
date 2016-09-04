package myjave.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Magician extends Human implements Skill{
	public Magician(){
		setHP(50);
		setAttack(20);
	}
	@Override
	public int action() {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = 1;
		try {
			System.out.print("Choose(Default defend)(1)defend(2)fire(3)treatment:");
			choice = Integer.parseInt(br.readLine());
			switch(choice){
			case 1:
				return 2;
			case 2:
				this.skill_act();
				return 1;
			case 3:
				return 3;
			default:
				return 2;
			}
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//System.out.println("Wrong Input3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("Wrong Input4");
		}
		return 2;
		
	}

	@Override
	public int skill_act() {
		// TODO Auto-generated method stub
		setAttack((int) (getAttack()*3.5));
		return 0;
	}

	@Override
	public int skill_act1() {
		// TODO Auto-generated method stub
		Random random = new Random();
		int heal;
		heal =random.nextInt(35)+1;
		System.out.println("HP increase :"+heal);
		setHP(getHp()+heal);
		return 0;
	}

}
