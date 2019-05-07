package graphicUserInterface;

import databaseInterfaceLayer.RetrieveDBO;
import databaseInterfaceLayer.RetrieveDBO.*;
import businessLogicLayer.Flight;
import businessLogicLayer.Flight.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import businessLogicLayer.Booking;
import java.util.ArrayList;
import businessLogicLayer.Account;

public class FlightTable extends Application {

	TableView<Flight> table;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		TableColumn<Flight, Integer> flightnum = new TableColumn<>("Flight #");
		flightnum.setMinWidth(50);
		flightnum.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));

		TableColumn<Flight, String> flightDate = new TableColumn<>("Flight Date");
		flightDate.setMaxWidth(100);
		flightDate.setCellValueFactory(new PropertyValueFactory<>("flightDate"));

		TableColumn<Flight, String> departcityColumn = new TableColumn<>("Departure");
		departcityColumn.setMinWidth(50);
		departcityColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));

		TableColumn<Flight, String> destination = new TableColumn<>("Destination");
		destination.setMinWidth(50);
		destination.setCellValueFactory(new PropertyValueFactory<>("destinationCity"));

		TableColumn<Flight, String> departtimeColumn = new TableColumn<>("Depart Time");
		departtimeColumn.setMinWidth(50);
		departtimeColumn.setCellValueFactory(new PropertyValueFactory<>("departTime"));

		TableColumn<Flight, String> arriveTimeColumn = new TableColumn<>("Arrive Time");
		arriveTimeColumn.setMaxWidth(100);
		arriveTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arriveTime"));

		TableColumn<Flight, String> returnFlightColumn = new TableColumn<>("Return Date");
		returnFlightColumn.setMaxWidth(100);
		returnFlightColumn.setCellValueFactory(new PropertyValueFactory<>("returnFlight"));

		TableColumn<Flight, String> seatColumn = new TableColumn<>("Available Seats");
		seatColumn.setMinWidth(100);
		seatColumn.setMaxWidth(100);
		seatColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));

		table = new TableView<>();
		table.setItems(flightSearch.flights);
		table.getColumns().addAll(flightnum, flightDate, departcityColumn, destination, departtimeColumn,
				arriveTimeColumn, returnFlightColumn, seatColumn);

		Label label1 = new Label(" Enter Flight Number ");

		BorderPane.setAlignment(label1, Pos.CENTER);

		TextField inputFlightNum = new TextField("Flight Number");
		inputFlightNum.setText("Enter Flight No.");
		inputFlightNum.setMaxWidth(100);

		Button bookflight = new Button("Press to Book Flight");

		Button backButton = new Button(" <-Back ");

		// Set the alignment of the Top Text to Center
		BorderPane.setAlignment(bookflight, Pos.CENTER_RIGHT);

		// Set the alignment of the Left Text to Center
		BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
		// Set the alignment of the Right Text to Center
		BorderPane.setAlignment(inputFlightNum, Pos.CENTER);

		// Create a BorderPane with a Text node in each of the five regions
		BorderPane bp = new BorderPane();

		bp.setTop(label1);
		bp.setCenter(inputFlightNum);
		bp.setRight(bookflight);
		bp.setLeft(backButton);
		bp.setBottom(table);

		bp.setPrefSize(700, 600);

		// CSS code Below
		bp.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 5;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");

		Scene scene = new Scene(bp);

		primaryStage.setTitle(" Availiable Flights ");
		scene.getStylesheets().add("graphicUserInterface/thing.css");
		primaryStage.setScene(scene);
		primaryStage.show();

		bookflight.setOnAction(e -> {

			int flightnumber = Integer.parseInt(inputFlightNum.getText());

			Booking.bookFlight(flightnumber);

		});

		backButton.setOnAction(e -> {
			flightSearch flightsearch = new flightSearch();
			try {
				flightsearch.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

}
