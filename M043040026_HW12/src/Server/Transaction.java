package Server;

public class Transaction {
	private int num;
	private int amount;
	private String tranType;
	private Account account;
	private String all_information;
	public Transaction(int num,int amount,String tranType,Account account){
		this.num = num;
		this.amount = amount;
		this.tranType = tranType;
		this.account = account;
		this.all_information = "Transaction[run:"+getNum()+","
				+ "account=Account[name:"+getAccount().getName()+", balance="+getAccount().getBalance()+"],"
				+ "amount="+getAmount()+","+"tranType="+getTrantype()+"]";
	}
	
	public String getTrantype(){
		return this.tranType;
	}
	public Account getAccount(){
		return this.account;
	}
	public int getAmount(){
		return this.amount;
	}
	public int getNum(){
		return this.num;
	}
	public String get_information(){
		return this.all_information;
	}
}
