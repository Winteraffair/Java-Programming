package myjava.homework;
//Represents a bank account

public class Account {
	private int accountNumber;
	private int pin;
	private int totalBalance;
	
	
	/* Fill your code here */
	public Account(int accountNumber,int pin,int totalBalance){
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.totalBalance = totalBalance;
		
	}
	public boolean validatePIN(int PIN){
		if(this.pin == PIN){
			return true;
		}
		return false;
	}
	public int getAccountNumber(){
		return accountNumber;
	}
	public int gettotalBalance(){
		return totalBalance;
	}
	public void credit(int money){
		this.totalBalance += money;
	}
	public void debit(int money){
		this.totalBalance -= money;
	}

}
