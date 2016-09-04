package Server;

public class Clerk implements Runnable {
	private QueueMachine queue;
	private String name;

	public Clerk(QueueMachine queue, String name) {
		this.queue = queue;
		this.name = name;
	}

	@Override
	public void run() {
		Transaction get_Tran;
		while (true) {
			try {
				while (queue.isEmpty()) {
					Thread.sleep(100);
				}
				if ((get_Tran = queue.get()) != null) {
					System.out.println(this.name + " 取得交易 " + get_Tran.get_information());
					synchronized (get_Tran.getAccount()) {
						write_account(get_Tran);
					}
				} else {
					System.out.println("didn't get");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	private void write_account(Transaction Tran) {
		if (Tran.getTrantype().equals("withdraw")) {
			// Tran.getAccount().setBalance(Tran.getAccount().getBalance() -
			// Tran.getAmount());

			int balance = Tran.getAccount().getBalance();
			balance = balance - Tran.getAmount();
			// wait 150ms
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tran.getAccount().setBalance(balance);

		} else {
			// Tran.getAccount().setBalance(Tran.getAccount().getBalance() +
			// Tran.getAmount());
			int balance = Tran.getAccount().getBalance();
			balance = balance + Tran.getAmount();
			// sleep 150ms
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// set
			Tran.getAccount().setBalance(balance);
		}
		System.out.println(this.name + " 完成" + Tran.getAccount().getName() + "的第" + Tran.getNum() + "筆交易 , 餘額為 "
				+ Tran.getAccount().getBalance());
	}

}
