// Represents a balance inquiry ATM transaction
package myjava.homework;
public class BalanceInquiry extends Transaction{

	public BalanceInquiry(int accountNumber, Screen msg_out, BankDatabase bank) {
		super(accountNumber, msg_out, bank);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		int balance = getbankDatabase().getTotalBalance(getAccountNumber());
		getScreen().displayMessageLine("Balance Information\nTotal Balance :"+Integer.toString(balance)+"");
		
	}

	/* Fill your code here */
	
}
