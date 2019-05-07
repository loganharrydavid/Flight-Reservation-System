package businessLogicLayer;

import databaseInterfaceLayer.InsertDBO;
import java.util.ArrayList;
import java.util.Random;
import businessLogicLayer.ExceptionHandler;
import databaseInterfaceLayer.*;

public class Account implements Comparable<Account> {

	private int accountID;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String state;
	private String email;
	private int zipCode;
	private int ssn;
	private String securityQuestion;
	private String securityAnswer;
	public static boolean is_Admin = false;

	public Account() {
	}

	public Account(String username, String password) {
		this.userName = username;
		this.password = password;

	}

	// Creates an Account object. Customer Accounts are set to is_Admin = false; and
	// Admin are set to true;

	public Account(int acct_id, String userName, String password, String firstname, String lastName, String address,
			String State, String email, int zipCode, int ssn, String sq, String sa) {

		this.firstName = firstname;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.email = email;
		this.state = State;
		this.zipCode = zipCode;
		this.ssn = ssn;
		this.securityQuestion = sq;
		this.securityAnswer = sa;
		this.accountID = acct_id;

	}

	// called from Registration to create a new users account
	public static void generateAccount(String fname, String lname, String address, String email, String state, int zip,
			int ssn, String un, String pword, String secQuestion, String sa) {

		int id = makeAccountID();

		Account acct = new Account(id, un, pword, fname, lname, address, state, email, zip, ssn, secQuestion, sa);

		InsertDBO input = new InsertDBO();

		input.insertAccount(acct);
		

	}

	public int getAccountID() {
		return accountID;
	}

	// generates a random account ID
	public static int makeAccountID() {

		Random rand = new Random();

		int ID = rand.nextInt(99999);

		return ID;
	}

	public void setaccount_id(int account_id) {

		this.accountID = account_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}


	@Override
	public String toString() {
		return "\nAccount ID: " + getAccountID() + "\nUsername " + this.getUserName() + "\nFirst name "
				+ this.getFirstName() + "\nLast name " + this.getLastName();
	}

	@Override
	public int compareTo(Account a) {
		if (ssn == a.getSsn()) {
			return 0;
		} else
			return -1;
	}

}
