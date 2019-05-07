package databaseInterfaceLayer;

import java.sql.*;
import java.util.ArrayList;
import businessLogicLayer.Account;
import businessLogicLayer.Flight;
import graphicUserInterface.AlertBox;
import businessLogicLayer.Booking;

public class InsertDBO {
	// location of database //set verify certificate to false to not use SSL to get
	// rid of the verification error
	static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
	static final String databaseUsername = "root";
	static final String databasePassword = "1234abcd";
	
	public static Boolean success;
	public ArrayList<Object> returnList;
	
	
	//Method to insert a new flight into the database
	public static void insertFlight(Flight flight) {
		
		success = false;
		try {

			Class.forName("java.sql.Driver");

			System.out.println("database connected! ");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			String sqlQuery = "INSERT INTO flights(flight_number,departure_city,destination_city,depart_time,"
					+ "arrive_time,flight_date,num_seats,return_day)" + " VALUES(?,?,?,?,?,?,?,?)";

			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, flight.getFlightNumber());
			preparedStatement.setString(2, flight.getDepartureCity());
			preparedStatement.setString(3, flight.getDestinationCity());
			preparedStatement.setString(4, flight.getDepartTime());
			preparedStatement.setString(5, flight.getArriveTime());
			preparedStatement.setString(6, flight.getFlightDate());
			preparedStatement.setInt(7, flight.getNumberOfSeats());
			preparedStatement.setString(8, flight.getReturnFlight());

			
			preparedStatement.executeUpdate();

			connection.close();
				success = true;
		} catch (Exception e) {
			System.out.println("something messed up in database! :-(");
			e.printStackTrace();
			success = false;
		}

	}

	//Method to insert new booking in the database
	public static void insertBooking(Booking booking){
		
		try {

			Class.forName("java.sql.Driver");

			System.out.println("database connected! ");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			String sqlQuery = "INSERT INTO bookedflights(ticket_number,flight_number,account_id,flight_date,"
					+ "flight_time,departCity,destinationCity,passenger_username,return_flight_Date)" 
			+ " VALUES(?,?,?,?,?,?,?,?,?)";
			
			String sql ="UPDATE flights SET num_seats = num_seats -1 WHERE flight_number=" + "'" + booking.getFlight_number() + "'";
			
			PreparedStatement ps2 = connection.prepareStatement(sql);
			ps2.executeUpdate();
			
		
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, booking.getTicketNumber());
			preparedStatement.setInt(2, booking.getFlight_number());
			preparedStatement.setInt(3, booking.getAccount_id());
			preparedStatement.setString(4, booking.getFlight_date());
			preparedStatement.setString(5, booking.getFlight_time());
			preparedStatement.setString(6, booking.getDeparteCity());
			preparedStatement.setString(7, booking.getDestinationCity());
			preparedStatement.setString(8, booking.getPassenger_userName());
			preparedStatement.setString(9, booking.getReturnFlight());
			

			preparedStatement.executeUpdate();

			connection.close();
			
			AlertBox.display("Thank you for flying with us", "You reservation is for flight: " + booking.getFlight_number()
			+ "\nTicket number is: " + booking.getTicketNumber());

		}catch (SQLIntegrityConstraintViolationException e1) {

				AlertBox.display("Duplicate Booking Alert!", "You already have a reservation booked for this flight" +
				"\nCheck that you entered the correct flight number");
				e1.printStackTrace();
				
			}catch(SQLException e2) {
				System.out.println("Syntax Error in SQL");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	

	}
	//Method to call to insert a new account into the database
	public void insertAccount(Account account) {
		
		 success = false;
		try {

			Class.forName("java.sql.Driver");

			System.out.println("database connected! ");

			
			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			String sqlQuery = "INSERT INTO account(account_id,username,password,firstname,lastname,address,"
					+ "state,email,zipcode,ssn,security_q,security_a,is_Admin)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, account.getAccountID());
			preparedStatement.setString(2, account.getUserName());
			preparedStatement.setString(3, account.getPassword());
			preparedStatement.setString(4, account.getFirstName());
			preparedStatement.setString(5, account.getLastName());
			preparedStatement.setString(6, account.getAddress());
			preparedStatement.setString(7, account.getState());
			preparedStatement.setString(8, account.getEmail());
			preparedStatement.setInt(9, account.getZipCode());
			preparedStatement.setInt(10, account.getSsn());
			preparedStatement.setString(11, account.getSecurityQuestion());
			preparedStatement.setString(12, account.getSecurityAnswer());
			preparedStatement.setBoolean(13, Account.is_Admin);

			preparedStatement.executeUpdate();
			
			connection.close();
			
			success = true;
			
		}catch(SQLIntegrityConstraintViolationException ex) {
			
			AlertBox.display("Duplicate Account Alert!", "We have an account already with your email address" 
					+ "\nPlease try again or use our password recovery system");
			success = false;
			
		}catch (SQLException e1) {
			e1.printStackTrace();

		} catch (ClassNotFoundException e2) {
			System.out.println("something messed up in database! :-(");
			e2.printStackTrace();
		}
		
	}
		
}
