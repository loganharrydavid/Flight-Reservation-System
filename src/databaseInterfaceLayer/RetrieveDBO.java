package databaseInterfaceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import businessLogicLayer.Account;
import businessLogicLayer.Booking;
import businessLogicLayer.Flight;
import businessLogicLayer.Flight.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RetrieveDBO {

	static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
	static final String databaseUsername = "root";
	static final String databasePassword = "1234abcd";


	// retrieves an Account from the DB. Use any user_name in the Account to tell
	// it which one to return
	public static Account retrieveAccount(String un) {
		
		Account account = new Account();

		try {

			Class.forName("java.sql.Driver");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM account WHERE username=" + "'" + un + "'");

			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {

				account.setaccount_id(res.getInt("account_id"));
				account.setUserName(res.getString("username"));
				account.setPassword(res.getString("password"));
				account.setFirstName(res.getString("firstname"));
				account.setLastName(res.getString("lastname"));
				account.setAddress(res.getString("address"));
				account.setState(res.getString("state"));
				account.setEmail(res.getString("email"));
				account.setZipCode(res.getInt("zipcode"));
				account.setSsn(res.getInt("ssn"));
				account.setSecurityQuestion(res.getString("security_q"));
				account.setSecurityAnswer(res.getString("security_a"));
				
				
				

			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return account;

		}

	
	//Returns an ObservableList of all Flights from the database
	public static ObservableList<Flight> getFlights() {
		
		 ObservableList<Flight> flights = FXCollections.observableArrayList();

		
		try {

			Class.forName("java.sql.Driver");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM flights");

			ResultSet rs = preparedStatement.executeQuery();
			
			

			while (rs.next()) {
				
				Flight flight = new Flight();

				flight.setflightnum(rs.getInt("flight_number"));
				flight.setDepartureCity(rs.getString("departure_city"));
				flight.setDestinationCity(rs.getString("destination_city"));
				flight.setDepartTime(rs.getString("depart_time"));
				flight.setArriveTime(rs.getString("arrive_time"));
				flight.setFlightDate(rs.getString("flight_date"));
				flight.setReturnFlight(rs.getString("return_day"));
				flight.setNumberOfSeats(rs.getInt("num_seats"));
				
				flights.add(flight);
			}

			connection.close();

		} catch (Exception e1) {
			e1.printStackTrace();

		}
		return flights;
		
	}

	public static ObservableList<Booking> retrieveBookings(int account_id) {

		 ObservableList<Booking> bookings = FXCollections.observableArrayList();

		
		try {

			Class.forName("java.sql.Driver");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM bookedflights WHERE account_id=" + "'" + account_id + "'");

			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				
				Booking booked = new Booking(res.getInt("ticket_number"),
											(res.getInt("flight_number")),
											(res.getString("flight_date")),
											(res.getString("flight_time")),
											(res.getString("departCity")),
											(res.getString("destinationCity")),
											(res.getString("return_flight_date")));
						
		
				
				bookings.add(booked);

			}

			connection.close();

		} catch (Exception e1) {
			e1.printStackTrace();

		}
	
	return bookings;

	}
	
	public static Flight retrieveFlight(int flightnum) {

		Flight flight = new Flight();
		try {

			Class.forName("java.sql.Driver");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM flights WHERE flight_number=" + "'" + flightnum + "'");

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				flight.setflightnum(rs.getInt("flight_number"));
				flight.setDepartureCity(rs.getString("departure_city"));
				flight.setDestinationCity(rs.getString("destination_city"));
				flight.setDepartTime(rs.getString("depart_time"));
				flight.setArriveTime(rs.getString("arrive_time"));
				flight.setFlightDate(rs.getString("flight_date"));
				flight.setReturnFlight(rs.getString("return_day"));
				flight.setNumberOfSeats(rs.getInt("num_seats"));

			}

			connection.close();

		} catch (Exception e1) {
			e1.printStackTrace();

		}
		return flight;
		
	}


}
