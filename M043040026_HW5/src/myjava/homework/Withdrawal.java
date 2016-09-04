// Represents a withdrawal ATM transaction
package myjava.homework;

public class Withdrawal extends Transaction{
	/* Fill your code here */
	private int amount;
	Keypad msg_input = new Keypad();
	public Withdrawal(int accountNumber, Screen msg_out, BankDatabase bank) {
		super(accountNumber, msg_out, bank);
		// TODO Auto-generated constructor stub
	}
	public void execute() {
		getScreen().displayMessageLine("How much do you want to withdraw:");
		amount = msg_input.getInput();
		if( amount== 0){
			getScreen().displayMessageLine("Invalid Input.");
			return;
		}else{
			
			if(amount > getbankDatabase().getTotalBalance(getAccountNumber())){
				getScreen().displayMessageLine("Insufficient funds in your account");
			}
			else{
				getScreen().displayMessageLine("Success");
				getbankDatabase().debit(getAccountNumber(),amount);
			}
		}
		
		
	}
}
