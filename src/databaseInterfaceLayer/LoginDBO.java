package databaseInterfaceLayer;

import java.sql.*;
import businessLogicLayer.ExceptionHandler;

public class LoginDBO {

	static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
	static final String databaseUsername = "root";
	static final String databasePassword = "1234abcd";
	static Connection connection;

	public String loginConn(String username) {

		String result = "";

		try {

			Class.forName("java.sql.Driver");

			System.out.println("databse connected! ");

			connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM account WHERE username=" + "'" + username + "'");

			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) {

				result = res.getString("password");

			}

			connection.close();

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Something went wrong with the database :(");
			ex.printStackTrace();

		}
		return result;
	}

	public Boolean searchFor(String email) {

		Boolean result = false;
		int check = 0;		
		try {

			connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM account WHERE username=" + "'" + email + "'");

			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) {

				check = res.getInt("ssn");

			}

			connection.close();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		if (check > 0) {
			result = true;
		} else {
			result = false;
		}
		return result;

	}

	public String returnPassword(String email, String security_A) {

		String result = "";
		String correctAns = "";
		String pword = "";
		String userAns = security_A;
		
		try {

			connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM account WHERE email=" + "'" + email + "'");

			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) {
				
				pword = res.getString("password");
				correctAns = res.getString("security_a");
				
				System.out.println(pword + correctAns);
				
			}

			if (!userAns.equals(correctAns)) {
				result = "Incorrect answer to security question";
			

			}else if (userAns.equals(correctAns)) {
				result = "Your password is: "  + pword; 
				
			}else {
				result = "I'm sorry we dont have an account with that email address";
				
				}
			
		

			connection.close();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		return result;

	}
}
