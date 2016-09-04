package myjave.homework;

public abstract class Human {
	private int hp;
	private int attack;
	
	public int getHp(){
		return hp;
	}
	public void setHP(int hp){
		this.hp = hp;
	}
	public int getAttack(){
		return attack;
	}
	public void setAttack(int attack){
		this.attack = attack;
	}
	public abstract int action();
}
