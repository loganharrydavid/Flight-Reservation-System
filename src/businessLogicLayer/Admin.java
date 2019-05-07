package businessLogicLayer;

public class Admin extends Account{
	
	 public final static Boolean is_Admin = true;

	public Admin(String username,String password) {
		super(username,password);
		
	}
	public Admin(int acct_id, String userName, String password, String firstname, String lastName, String address,
			String State, String email, int zipCode, int ssn, String sq, String sa) {
		
		super(acct_id,userName,password,firstname,lastName,address,State,email,zipCode,ssn,sq,sa);
		
		
	}
	
	

	@Override
	public String toString(){
		return "\nUsername: " + this.getUserName() + "\npassword: " + this.getPassword() 
		+ "\nAdmin Status: " + is_Admin;
		
	}
}