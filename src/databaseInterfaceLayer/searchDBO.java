package databaseInterfaceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import businessLogicLayer.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class searchDBO {
	static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
	static final String databaseUsername = "root";
	static final String databasePassword = "1234abcd";

	
	
	public static ObservableList<Flight> findFlights(String departFrom,String arriveTo,
			String departDay,String returnDay){
		
		ObservableList<Flight> flights = FXCollections.observableArrayList();

		
		try {
			
			Class.forName("java.sql.Driver");

			System.out.println("databse connected! ");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM flights WHERE departure_city=" + "'" + departFrom + "'" + 
			"and destination_city=" + "'" + arriveTo + "'" + "and flight_date=" + "'" + departDay + "'" 
							+ "and return_day=" + "'" + returnDay + "'");

			ResultSet rs = preparedStatement.executeQuery();
			
			
			
			while(rs.next()) {
				
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
				
		
	}catch(Exception ex) {
		
		System.out.println("Something went wrong with the database");
	
		
	}
		return flights;
		
}
	
}
