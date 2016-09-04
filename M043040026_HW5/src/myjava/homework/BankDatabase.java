// Represents the bank account information database
package myjava.homework;
public class BankDatabase {
	
	private Account[] accounts; // array of Accounts
	
    // no-argument BankDatabase constructor initializes accounts
	public BankDatabase () {
		accounts = new Account[2];  // just 2 accounts for testing
		accounts[0] = new Account(12345, 54321, 1000);
		accounts[1] = new Account(67890, 98765, 100);
	}


	
	/* Fill your code here */
	public boolean authenticateUser(int account,int pin){
		for(int i=0;i<accounts.length;i++){
			//System.out.println(accounts[i].getAccountNumber()+":"+ accounts[i].validatePIN(pin));
			if((accounts[i].getAccountNumber() == account) && accounts[i].validatePIN(pin)){
				return true;
			}
		}
		return false;
		
	}
	public int getTotalBalance(int userAccountNumber){
		for(int i=0;i<accounts.length;i++){
			if(accounts[i].getAccountNumber() == userAccountNumber ){
				return accounts[i].gettotalBalance();
			}
		}
		return 0;
	}
	public void credit(int userAccountNumber,int amount){
		for(int i=0;i<accounts.length;i++){
			if(accounts[i].getAccountNumber() == userAccountNumber ){
				accounts[i].credit(amount);
			}
		}
	}
	public void debit(int userAccountNumber,int amount){
		for(int i=0;i<accounts.length;i++){
			if(accounts[i].getAccountNumber() == userAccountNumber ){
				accounts[i].debit(amount);
			}
		}
	}
	
}
