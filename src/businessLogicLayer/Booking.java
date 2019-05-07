package businessLogicLayer;

import java.util.Random;
import graphicUserInterface.Login;
import databaseInterfaceLayer.InsertDBO;
import databaseInterfaceLayer.RetrieveDBO;



public class Booking {

	private int ticketNumber;
	private int account_id;
	private int flight_number;
	private String flight_date;
	private String flight_time;
	private String departeCity;
	private String destinationCity;
	private String passenger_userName;
	private String returnFlight;


	public Booking() {

	}

	public Booking(int ticket_num,int acctID, int flightNum ,String flyDate,
			String flyTime,String departCity, String destinCity, String pass_username,String returnFly) {

		this.ticketNumber = ticket_num;
		this.account_id = acctID;
		this.flight_number = flightNum;
		this.flight_date = flyDate;
		this.flight_time = flyTime;
		this.passenger_userName = pass_username;
		this.returnFlight = returnFly;
		this.departeCity = departCity;
		this.destinationCity = destinCity;

	}
	public Booking(int ticket,int flight,String fdate,String ftime,String depCity,String desCity,String rFlight) {
		
		this.destinationCity = desCity;
		this.departeCity = depCity;
		this.ticketNumber = ticket;
		this.flight_number = flight;
		this.flight_date = fdate;
		this.flight_time = ftime;
		this.returnFlight = rFlight;
	}
	
	//this will combine the info from the Flight the person books with their Account info and 
	//use InsertDBO to insert it into the database
	public static void bookFlight(int flightNumber) {
		
		int ticketNum = makeTicketNumber();
		
		Flight toBook = RetrieveDBO.retrieveFlight(flightNumber);
	
		Booking booking = new Booking(ticketNum,Login.currentAccount.getAccountID(),
									  flightNumber,toBook.getFlightDate(),toBook.getDepartTime(),
									  toBook.getDepartureCity(),toBook.getDestinationCity(),
									  Login.currentAccount.getUserName(),
									  toBook.getReturnFlight());

		InsertDBO.insertBooking(booking);
		
	}
	public int getAccount_id() {
		return account_id; 
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(int flight_number) {
		this.flight_number = flight_number;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public static int makeTicketNumber() {

		Random rand = new Random();

		int tkn = rand.nextInt(999);

		return tkn;
	}
	public void setTicketNum(int ticketNum) {
		this.ticketNumber = ticketNum;
		
	}

	public String getFlight_date() {
		return flight_date;
	}

	public void setFlight_date(String flightdate) {
		this.flight_date = flightdate;
	}

	public String getFlight_time() {
		return flight_time;
	}

	public void setFlight_time(String flight_time) {
		this.flight_time = flight_time;
	}
	public String getPassenger_userName() {
		return passenger_userName;
	}

	public void setPassenger_userName(String passenger_userName) {
		this.passenger_userName = passenger_userName;
	}
	public String getReturnFlight() {
		return returnFlight;
	}

	public void setReturnFlight(String returnFlight) {
		this.returnFlight = returnFlight;
	}

	public String getDeparteCity() {
		return departeCity;
	}

	public void setDeparteCity(String departeCity) {
		this.departeCity = departeCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

}