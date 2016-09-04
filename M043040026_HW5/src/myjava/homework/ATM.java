package myjava.homework;

public class ATM {
	private boolean userAuthenticated;
	private BankDatabase bank = new BankDatabase();
	private Screen msg_out = new Screen();
	private Keypad msg_input = new Keypad();
	private Transaction transaction;
	/* Fill your code here */
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			try {
				int account_check = 0, pin_check = 0 , log_out=0;
				userAuthenticated = false;
				do {
					msg_out.displayMessageLine("Welcome !");
					msg_out.displayMessage("Please enter your account number:");
					account_check = msg_input.getInput();
					msg_out.displayMessage("Please enter your pin:");
					pin_check = msg_input.getInput();
					if ((account_check * pin_check) == 0) {
						msg_out.displayMessageLine("Invalid account or Pin. Please try again.\n");
					} else {
						userAuthenticated = bank.authenticateUser(account_check, pin_check);
						if (!userAuthenticated) {
							msg_out.displayMessageLine("Invalid account or Pin. Please try again.\n");
						}
						
					}
				} while (!userAuthenticated);

				while (log_out==0) {
					int action = 0;
					msg_out.displayMessage("\nMain_menu:\n1. View my balance\n2. Withdraw\n3. Deposit\n4. Exit");
					msg_out.displayMessage("\nEnter a choice:");
					action = msg_input.getInput();
					switch (action) {
					case 1:
						transaction = new  BalanceInquiry(account_check,msg_out,bank);
						transaction.execute();
						break;
					case 2:
						transaction = new  Withdrawal(account_check,msg_out,bank);
						transaction.execute();
						break;
					case 3:
						transaction = new  Deposit(account_check,msg_out,bank);
						transaction.execute();
						break;
					case 4:
						log_out = 1;
						msg_out.displayMessageLine("EXIT.");
						msg_out.displayMessageLine("Thank you ! Goodbye!\n");
						break;
					default:
						msg_out.displayMessageLine("You did not enter a valid selection. Try again.");
					}

				}
			} catch (Exception ex) {
				msg_out.displayMessageLine("Invalid range of number, Please Log in again\n");
			}
		}
	}
}
