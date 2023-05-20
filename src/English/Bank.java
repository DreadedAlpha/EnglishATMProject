package English;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	private String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	public String getNewUserUUID() {
		
		//in its
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique = false;
		
		// loop until we get a unique ID
		do {
			//generating the number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			// Check for Uniqueness
			nonUnique = false; 
			for(User u: this.users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
					//loops back if nonUnique = true
				}
			}
			
		} while(nonUnique) ;
		
		return uuid;
	}
	
	
	public String getNewAccountUUID() {
		 
		//in its
		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique = false;
		
		// loop until we get a unique ID
		do {
			//generating the number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			// Check for Uniqueness
			nonUnique = false; 
			for(Account a: this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
					//loops back if nonUnique = true
				}
			}
			
		} while(nonUnique) ;
		
		return uuid;	
	}
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	public User addUser(String firstName, String lastName, String pin) {
		
		// create a new User object and add it to our list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		//create a savings account
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	
	public User userLogin(String userID, String pin) {
		
		for (User u: this.users) {
			
			//check users ID if its correct
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		
		//if user doesn't exist or pin is incorrect
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
}	
	




