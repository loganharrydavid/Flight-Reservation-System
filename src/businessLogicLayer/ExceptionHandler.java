package businessLogicLayer;

import businessLogicLayer.Account;

public class ExceptionHandler extends Exception{
	
	public ExceptionHandler(String message) {
		super(message);

	}
		
		//this method is called when a new user tries to register an account but before it is stored in the database
		//the purpose is to check the format of their input (i.e.) email address, SSN, etc...
		public static void checkRegistration(Account account) throws Exception {
			
			
			try {
			
			String ssnString = "" + account.getSsn();		
			
			int lengthOfSsn = String.valueOf(ssnString).length();
			
			if(lengthOfSsn != 9) {
				throw new Exception("Social Security No. must be 9 numbers");
			}
			}catch(Exception e) {
				e.getMessage();
				
		}
		
		}
	}
	
	