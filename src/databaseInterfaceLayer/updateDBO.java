package databaseInterfaceLayer;

import java.sql.*;
import businessLogicLayer.Flight;

public class updateDBO {
	
	static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
	static final String databaseUsername = "root";
	static final String databasePassword = "1234abcd";
	static Connection connection;
	public static Boolean result;
	

	

	public static void deleteFlight(int flightNum) {
		

		result = false;
		try {

			Class.forName("java.sql.Driver");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM flights WHERE flight_number=" + "'" + flightNum + "'");

			preparedStatement.executeUpdate();
			
			
			connection.close();
			result = true;
		} catch (Exception e1) {
			e1.printStackTrace();
			result = false;
		
		}
	}
	
	public static void deleteBooking(int ticketNumber) {
		
		result = false;
		try {

			Class.forName("java.sql.Driver");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM bookedflights WHERE ticket_number=" + "'" + ticketNumber + "'");

			preparedStatement.executeUpdate();
			
			
			
			connection.close();
			result = true;
		} catch (Exception e1) {
			e1.printStackTrace();
			result = false;

		}
		
	}
		
		
		
		
		
		 
		
	}
	


