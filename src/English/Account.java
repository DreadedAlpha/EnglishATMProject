package English;

import java.util.ArrayList;

public class Account {

	private String name;
	
	//User's ID Number
	private String uuid;
	// The User that owns the account
	private User holder;
	// The List of Transactions
	private ArrayList<Transaction> transactions;
	
	public Account(String name, User holder, Bank theBank) {
		//set the account name and holder
		this.name = name;
		this.holder = holder;
		
		//get new account UUID
		this.uuid = theBank.getNewAccountUUID();
		
		//in it transactions
		this.transactions = new ArrayList<Transaction>();
		
	}
	
	public String getUUID()	{
		return this.uuid;
	}
	
	public String getSummaryLine() {
		// get the balance of the account
		double balance = this.getBalance();
		
		// format the summary line (if balance is negative)
		// f -> floating, .02 -> 2 digits after decimal
		// %s prints the account ID
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid,
					balance, this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid,
					balance, this.name);
			
		}
	}
	
	public double getBalance() {
		double balance = 0;
		for (Transaction t: this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}
	
	public void printTransHistory() {
		System.out.printf("\nTransaction history for accounts\n", this.uuid);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	} 
	
	public void addTransaction(double amount, String memo) {
		// create new transaction object and add it to the list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
	}
}
