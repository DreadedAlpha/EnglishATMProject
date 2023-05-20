package English;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {
	// Private means it's only accessible within the class
	private String firstName;
	private String lastName;
	// User's ID Number
	private String uuid;
	// User's MD5 Hashed Pin Number
	private byte pinHash[];
	// User's Accounts List
	private ArrayList<Account> accounts;
	
	public User(String firstName, String lastName, String pin, Bank theBank) {
		
		// set user's name
		// this refers to the object being called
		this.firstName = firstName;
		this.lastName = lastName;
		
		// store the pin's MD5 hash for security
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
			//Makes sure everything runs smoothing without errors
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		//get a new, unique universal ID for the user
		this.uuid = theBank.getNewUserUUID();
		//empty list of accounts
		this.accounts = new ArrayList<Account>();
		//print log message
		System.out.printf("New user %s, %s with ID %s created. \n", 
				           lastName, firstName, this.uuid);
	}
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public boolean validatePin(String aPin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()),
					this.pinHash);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("  (%d) %s\n", a+1,
					this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}
	
	public int numAccounts() {
		return this.accounts.size();
	}
	
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}
	
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
		
	}
	
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
	
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
	
	
	
}

