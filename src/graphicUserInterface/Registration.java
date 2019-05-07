package graphicUserInterface;

import businessLogicLayer.Account.*;
import businessLogicLayer.Account;
import databaseInterfaceLayer.LoginDBO;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class Registration extends Application {

	Scene scene;
	GridPane grid;
	Label firstName;
	Label lastName;
	Label address;
	Label zipCode;
	Label state;
	Label username;
	Label email;
	Label passwords;
	Label confirmPasswords;
	Label SSN;
	Label SecurityQuestion;
	Label SecurityAnswer;
	TextField firstNameInput;
	TextField lastNameInput;
	TextField addressInput;
	TextField zipCodeInput;
	TextField stateInput;
	TextField userNameInput;
	TextField emailInput;
	PasswordField passwordInput;
	PasswordField confirmPasswordInput;
	TextField SSNInput;
	TextField SecurityAnswerInput;
	Button submitButton;
	Button backButton;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		// First name
		firstName = new Label("First Name:");
		GridPane.setConstraints(firstName, 0, 0);
		firstNameInput = new TextField();
		firstNameInput.setPromptText(" Enter First Name ");
		GridPane.setConstraints(firstNameInput, 0, 1);

		// Last Name
		lastName = new Label("Last Name:");
		GridPane.setConstraints(lastName, 0, 2);
		lastNameInput = new TextField();
		lastNameInput.setPromptText(" Enter Last Name ");
		GridPane.setConstraints(lastNameInput, 0, 3);

		// Address
		address = new Label(" Address: ");
		GridPane.setConstraints(address, 0, 4);
		addressInput = new TextField();
		addressInput.setPromptText(" Enter the Address ");
		GridPane.setConstraints(addressInput, 0, 5);

		// State
		state = new Label(" State: ");
		GridPane.setConstraints(state, 0, 6);
		stateInput = new TextField();
		stateInput.setPromptText(" Enter the State ");
		GridPane.setConstraints(stateInput, 0, 7);

		// Zip Code
		zipCode = new Label(" Zip Code: ");
		GridPane.setConstraints(zipCode, 0, 8);
		zipCodeInput = new TextField();
		zipCodeInput.setPromptText(" Enter Zip Code ");
		GridPane.setConstraints(zipCodeInput, 0, 9);

		// UserName
		username = new Label("Enter Username:");
		GridPane.setConstraints(username, 1, 0);
		userNameInput = new TextField();
		userNameInput.setPromptText(" Enter Username ");
		GridPane.setConstraints(userNameInput, 1, 1);

		// Passwords
		passwords = new Label(" Password: ");
		GridPane.setConstraints(passwords, 1, 2);
		passwordInput = new PasswordField();
		passwordInput.setPromptText("Enter you passwords ");
		GridPane.setConstraints(passwordInput, 1, 3);

		// Confirm Passwords
		confirmPasswords = new Label(" Confrim Passwords: ");
		GridPane.setConstraints(confirmPasswords, 1, 4);
		confirmPasswordInput = new PasswordField();
		confirmPasswordInput.setPromptText(" EnterConfirm Password ");
		GridPane.setConstraints(confirmPasswordInput, 1, 5);

		// Email
		email = new Label("Email: ");
		GridPane.setConstraints(email, 2, 0);
		emailInput = new TextField();
		emailInput.setPromptText("Enter Email Address: ");
		GridPane.setConstraints(emailInput, 2, 1);

		// SSN
		SSN = new Label(" Soical security number: ");
		GridPane.setConstraints(SSN, 1, 6);
		SSNInput = new TextField();
		SSNInput.setPromptText(" Enter SSN ");
		GridPane.setConstraints(SSNInput, 1, 7);

		// Security Question
		SecurityQuestion = new Label(" Security Question ");
		GridPane.setConstraints(SecurityQuestion, 1, 8);

		ChoiceBox<String> choicebox = new ChoiceBox<>();
		GridPane.setConstraints(choicebox, 1, 9);

		choicebox.getItems().add(" What is your country Origin ");
		choicebox.getItems().add(" What is your mother middle name ");
		choicebox.getItems().add(" What is your first Elemenatry School ");
		choicebox.getItems().add(" What is your pet name ");
				
		SecurityAnswer = new Label(" Security Answer ");
		GridPane.setConstraints(SecurityAnswer, 1, 10);
		SecurityAnswerInput = new TextField();
		SecurityAnswerInput.setPromptText(" Answer ");
		GridPane.setConstraints(SecurityAnswerInput, 1, 11);

		submitButton = new Button(" Submit Registration ");
		GridPane.setConstraints(submitButton, 0, 10);
		// Need to add Security Answer

		backButton = new Button(" <-Back ");
		GridPane.setConstraints(backButton, 0, 11);

		grid.getChildren().addAll(firstName, firstNameInput, lastName, lastNameInput, address, addressInput, zipCode,
				zipCodeInput, email, emailInput, state, stateInput, username, userNameInput, passwords, passwordInput,
				confirmPasswords, confirmPasswordInput, SSN, SSNInput, SecurityAnswer, choicebox, SecurityAnswerInput,
				submitButton, backButton);

		scene = new Scene(grid, 700, 700);
		primaryStage.setTitle("Registration");
		scene.getStylesheets().add("graphicUserInterface/thing.css");
		primaryStage.setScene(scene);
		primaryStage.show();

		submitButton.setOnAction(e -> {
			
			String readQuestion = choicebox.getValue();
			
			
			if(firstNameInput.getText().isEmpty()|| lastNameInput.getText().isEmpty() || addressInput.getText().isEmpty()
					|| emailInput.getText().isEmpty() || stateInput.getText().isEmpty() || zipCodeInput.getText().isEmpty()
					|| SSNInput.getText().isEmpty()|| userNameInput.getText().isEmpty() || passwordInput.getText().isEmpty()
					|| readQuestion.isEmpty() || SecurityAnswerInput.getText().isEmpty()) {
				
				
				AlertBox.display("Blank registration field", "Please fill in all info on registration form");
				
			}else {
					
			Account.generateAccount(firstNameInput.getText(), lastNameInput.getText(), addressInput.getText(),
					emailInput.getText(), stateInput.getText(), Integer.parseInt(zipCodeInput.getText()),
					Integer.parseInt(SSNInput.getText()), userNameInput.getText(), passwordInput.getText(),
					readQuestion, SecurityAnswerInput.getText());

			if (databaseInterfaceLayer.InsertDBO.success) {

				AlertBox.display("Succesful Registration Message",
						"Thank you for registering with us" + "\nPlease login to book a flight");

				Login login = new Login();

				try {
					login.start(primaryStage);
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		}

		});

		backButton.setOnAction(e -> {
			Login login = new Login();
			try {
				login.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}
}
