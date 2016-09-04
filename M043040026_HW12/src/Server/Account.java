package Server;

public class Account {
	
	private String name;
	private int balance;
	
	public Account(String name,int balance){
		this.name = name;
		this.balance = balance;
	}
	
	public int getBalance(){
		return this.balance;
	}
	public void setBalance(int newbalance){
		this.balance = newbalance;
	}
	public String getName(){
		return this.name;
	}
}
