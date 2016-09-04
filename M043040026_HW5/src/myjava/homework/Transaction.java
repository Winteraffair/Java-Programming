// Abstract superclass Transaction represents an ATM transaction
package myjava.homework;
public abstract class  Transaction {
	
	/* Fill your code here */
	private int accountNumber;
	private Screen msg_out;
	private BankDatabase bank;
	
	public Transaction(int accountNumber,Screen msg_out, BankDatabase bank){
		this.accountNumber = accountNumber;
		this.msg_out = msg_out;
		this.bank = bank;
		
	}
	public int getAccountNumber(){
		return this.accountNumber;
	}
	public Screen getScreen(){
		return this.msg_out;
	}
	public BankDatabase getbankDatabase(){
		return this.bank;
	}
	public abstract void execute();
}