package graphicUserInterface;

import java.time.LocalDate;

import businessLogicLayer.Flight.*;
import javafx.application.Application;
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

public class AddFlight extends Application {

	GridPane grid;
	Scene scene;
	Label depatureCity;
	Label destinationCity;
	Label returnDate;
	Label depatureDate;
	Label leavingTime;
	Label ComingTime;
	Label numberOfSeats;
	Button addButton;
	Button depatureButton;
	Button destinationButton;
	LocalDate ld;
	LocalDate ld1;
	TextField passangerInput;
	Button backButton;
	TextField depatureTime;
	TextField returnTime;
	

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
        
		leavingTime = new Label(" Depature Time ");
		GridPane.setConstraints(leavingTime, 2, 0);
		
		depatureTime = new TextField();
		depatureTime.setPromptText(" Depature Time "); // this will display gray line in the box
		GridPane.setConstraints(depatureTime, 2, 1);
		
		ComingTime = new Label(" Arrive Time ");
		GridPane.setConstraints(ComingTime, 2, 2);
		
		returnTime = new TextField();
		returnTime.setPromptText(" Arrive Time "); // this will display gray line in the box
		GridPane.setConstraints(returnTime, 2, 3);
		
		
		Button addButton = new Button(" Add ");
		GridPane.setConstraints(addButton, 0, 8);

		backButton = new Button(" <-Back ");
		GridPane.setConstraints(backButton, 0, 9);
		
		depatureCity = new Label(" From* ");
		GridPane.setConstraints(depatureCity, 0, 0);

		numberOfSeats = new Label(" Number of Seat ");
		GridPane.setConstraints(numberOfSeats, 2, 4);
		
		ChoiceBox<String> choicebox2 = new ChoiceBox<>();
		GridPane.setConstraints(choicebox2, 2, 5);

	
		
		choicebox2.getItems().add("189");
		choicebox2.getItems().add("290");
		choicebox2.getItems().add("451");
		choicebox2.getItems().add("366");
		
		
		

		grid.getChildren().addAll(depatureCity, choicebox, destinationCity, choicebox1, dp, depatureDate, dp1,
				returnDate, addButton, backButton, depatureTime, leavingTime, returnTime, ComingTime, choicebox2, numberOfSeats);
		
		
		addButton.setOnAction(e->{
			
			String departFrom = choicebox.getValue();
			String whereTo = choicebox1.getValue();
			String departOn = ld.toString();
			String returnOn = ld1.toString();
			int seats = Integer.parseInt(choicebox2.getValue());
			
		businessLogicLayer.Flight.generateFlight(departFrom,whereTo, 
				depatureTime.getText(),returnTime.getText(),departOn,
					returnOn,seats);
			

			
		});
		
		
		
		
		backButton.setOnAction(e -> {
			AdminLogin adminscreen = new AdminLogin();
			try {
				adminscreen.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		scene = new Scene(grid, 600, 600, Color.DARKBLUE);
		scene.getStylesheets().add("graphicUserInterface/thing.css");
		primaryStage.setTitle(" Admin ");
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}
	


	
	

}

