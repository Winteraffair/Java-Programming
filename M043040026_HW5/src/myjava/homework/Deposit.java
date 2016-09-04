// Represents a deposit ATM transaction
package myjava.homework;
public class Deposit  extends Transaction{
	/* Fill your code here */
	
	
	private int amount;
	Keypad msg_input = new Keypad();
	public Deposit(int accountNumber, Screen msg_out, BankDatabase bank) {
		super(accountNumber, msg_out, bank);
		// TODO Auto-generated constructor stub
	}

	
	public void execute(){
		getScreen().displayMessageLine("How much do you want to deposit:");
		amount = msg_input.getInput();
		if( amount == 0){
			getScreen().displayMessageLine("Invalid Input.");
			return;
		}else{
				getScreen().displayMessageLine("Success");
				getbankDatabase().credit(getAccountNumber(),amount);
			
		}
	}
}
