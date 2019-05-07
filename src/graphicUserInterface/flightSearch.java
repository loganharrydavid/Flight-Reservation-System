package graphicUserInterface;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

import businessLogicLayer.Account;
import businessLogicLayer.Flight;
import databaseInterfaceLayer.searchDBO;
import graphicUserInterface.Login;

public class flightSearch extends Application {

	Scene scene;
	GridPane grid;
	Label depatureCity;
	Label destinationCity;
	Label depatureDate;
	Label returnDate;
	Label passanger;
	Button searchButton;
	Button depatureButton;
	Button destinationButton;
	LocalDate ld;
	LocalDate ld1;
	TextField passangerInput;
	Button backButton;
	public String departDay;
	public String returnDay;
	public String departFrom;
	public String arriveTo;
	public static ObservableList<Flight> flights;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		depatureCity = new Label(" From* ");
		GridPane.setConstraints(depatureCity, 0, 0);

		ChoiceBox<String> choicebox = new ChoiceBox<>();
		GridPane.setConstraints(choicebox, 0, 1);

		choicebox.getItems().add("Atlanta");
		choicebox.getItems().add("Winterfell");
		choicebox.getItems().add("Kings landings");
		choicebox.getItems().add("High Garden");
		choicebox.getItems().add("Sun Spear");
		choicebox.getItems().add("Summer Hall");

		destinationCity = new Label(" To* ");
		GridPane.setConstraints(destinationCity, 1, 0);

		ChoiceBox<String> choicebox1 = new ChoiceBox<>();
		GridPane.setConstraints(choicebox1, 1, 1);

		choicebox1.getItems().add("Atlanta");
		choicebox1.getItems().add("Winterfell");
		choicebox1.getItems().add("Kings landings");
		choicebox1.getItems().add("High Garden");
		choicebox1.getItems().add("Sun Spear");
		choicebox1.getItems().add("Summer Hall");

		depatureDate = new Label(" Depart Date ");
		GridPane.setConstraints(depatureDate, 0, 3);

		DatePicker dp = new DatePicker();
		dp.setOnAction(e -> {
			ld = dp.getValue();

		});

		StackPane root = new StackPane();
		root.getChildren().add(dp);
		GridPane.setConstraints(dp, 0, 4);

		returnDate = new Label(" Return Date ");
		GridPane.setConstraints(returnDate, 1, 3);

		DatePicker dp1 = new DatePicker();
		dp1.setOnAction(e -> {
			ld1 = dp1.getValue();

		});

		StackPane root1 = new StackPane();
		root1.getChildren().add(dp1);
		GridPane.setConstraints(dp1, 1, 4);

		Button search = new Button(" Search ");
		GridPane.setConstraints(search, 0, 8);

		backButton = new Button(" <-Back ");
		GridPane.setConstraints(backButton, 0, 9);

		grid.getChildren().addAll(depatureCity, choicebox, destinationCity, choicebox1, dp, depatureDate, dp1,
				returnDate, search, backButton);

		search.setOnAction(e -> {

			departFrom = choicebox.getValue();
			arriveTo = choicebox1.getValue();
			departDay = ld.toString();
			returnDay = ld1.toString();

			flights = searchDBO.findFlights(departFrom, arriveTo, departDay, returnDay);

			if (flights.isEmpty()) {

				AlertBox.display("No Flights Found", "There are currently no flights that match you search");

			} else {

				FlightTable flightTable = new FlightTable();

				try {

					flightTable.start(primaryStage);

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		});

		backButton.setOnAction(e -> {

				MainMenu mainmenu = new MainMenu();
				try {
					mainmenu.start(primaryStage);
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			
		});

		scene = new Scene(grid, 600, 600, Color.DARKBLUE);
		primaryStage.setTitle(" Book Flight ");
		scene.getStylesheets().add("graphicUserInterface/thing.css");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
