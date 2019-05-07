package graphicUserInterface;


import databaseInterfaceLayer.updateDBO;
import java.util.ArrayList;
import graphicUserInterface.Login;
import businessLogicLayer.Account;
import businessLogicLayer.Booking;
import businessLogicLayer.Flight;
import databaseInterfaceLayer.RetrieveDBO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MainMenu extends Application {

	Scene scene;
	GridPane grid;
	Label whatNext;
	Button bookFlight;
	Button backButton;
	TableView<Booking> table;
	TextField ticketNumber;
	
	
public static void main(String[] args) {
	launch(args);
}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		

		TableColumn<Booking, Integer> ticketNum = new TableColumn<>("Ticket #");
		ticketNum.setMinWidth(50);
		ticketNum.setCellValueFactory(new PropertyValueFactory<>("ticketNumber"));
		
		TableColumn<Booking, Integer> flightnum = new TableColumn<>("Flight #");
		flightnum.setMinWidth(70);
		flightnum.setCellValueFactory(new PropertyValueFactory<>("flight_number"));

		TableColumn<Booking, String> departDate = new TableColumn<>("Depart Date");
		departDate.setMinWidth(100);
		departDate.setCellValueFactory(new PropertyValueFactory<>("flight_date"));

		TableColumn<Booking, String> dTime = new TableColumn<>("Depart Time");
		dTime.setMinWidth(100);
		dTime.setCellValueFactory(new PropertyValueFactory<>("flight_time"));
		
		TableColumn<Booking, String> dcity = new TableColumn<>("Depart City");
		dcity.setMinWidth(100);
		dcity.setCellValueFactory(new PropertyValueFactory<>("departeCity"));
		
		TableColumn<Booking, String> descity = new TableColumn<>("Destination City");
		descity.setMinWidth(100);
		descity.setCellValueFactory(new PropertyValueFactory<>("destinationCity"));

		TableColumn<Booking, String> rFlight = new TableColumn<>("Return Date");
		rFlight.setMinWidth(100);
		rFlight.setCellValueFactory(new PropertyValueFactory<>("returnFlight"));

		
		ObservableList<Booking> list = RetrieveDBO.retrieveBookings(Login.currentAccount.getAccountID());
	
		table = new TableView<>();
		table.setItems(list);
		table.getColumns().addAll(ticketNum,flightnum, departDate, dTime,dcity,descity, rFlight);
		
		BorderPane borderpane = new BorderPane();
		
		Label whatNext = new Label("What would you like to do next?");
		BorderPane.setAlignment(whatNext,Pos.CENTER);
		
		Button bookFlight = new Button("Book A Flight");
		
		Button deleteFlight = new Button("Delete Flight");
		
		TextField deleteTicket = new TextField();
		deleteTicket.setPromptText("Ticket No. to Delete");
		
		backButton = new Button("<-Back ");
	
		Label booked = new Label("Current reservations:");
		BorderPane.setAlignment(booked, Pos.CENTER);
		
		Button logout = new Button("Log Out");
		
		ToolBar toolbar = new ToolBar(logout,backButton,bookFlight,whatNext,deleteTicket,deleteFlight);
		
	
		borderpane.setTop(toolbar);
		borderpane.setCenter(booked);
		borderpane.setBottom(table);		
		borderpane.setPrefSize(700,500);
		
	
		
		scene = new Scene(borderpane);
		scene.getStylesheets().add("graphicUserInterface/thing.css");
		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		logout.setOnAction(e->{
			
			Login loginScreen = new Login();
			
			try {
				loginScreen.start(primaryStage);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		
		bookFlight.setOnAction(e->{
			flightSearch fs = new flightSearch();
			try {
				fs.start(primaryStage);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		
		backButton.setOnAction(e->{ 
			
			if(Login.currentAccount.getUserName().contains("Admin")) {
				AdminLogin adm = new AdminLogin();
				
				try {
					adm.start(primaryStage);
					
				}catch(Exception e3) {
					e3.printStackTrace();
				}
			}else {
			Login login = new Login();
			try {
				login.start(primaryStage);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			}
		});
		
		deleteFlight.setOnAction(e->{
			
			int TicketNum = Integer.parseInt(deleteTicket.getText());
			try {
			updateDBO.deleteBooking(TicketNum);
			if(updateDBO.result == false) {
				throw new Exception("Something went wrong");
			}else {
				AlertBox.display("Flight Deleted", "You no longer have a booking for flight: " + TicketNum);
				
				try {
					MainMenu nextScreen = new MainMenu();
					nextScreen.start(primaryStage);
				} catch (Exception ex) {
					ex.printStackTrace();
				
				}
			}
			
			}catch(Exception ex) {
				AlertBox.display("Alert!!", ex.getMessage());
			}
			
			
		});
		
		
		
		

		
		
		
	}

}