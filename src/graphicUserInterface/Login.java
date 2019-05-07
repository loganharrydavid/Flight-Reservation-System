package graphicUserInterface;

import businessLogicLayer.loginObject;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import businessLogicLayer.Account;
import databaseInterfaceLayer.RetrieveDBO;
import businessLogicLayer.Admin;

public class Login extends Application {

	GridPane grid;
	Scene scene;
	Label username;
	Label password;
	TextField usernameInput;
	PasswordField passwordInput;
	Button loginButton;
	Color color;
	Button SignUpButton;
	Button ForgotPassword;

	
	public static Account currentAccount;
	public static Admin currentAdmin;
	public static String user;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		username = new Label(" Username : ");
		GridPane.setConstraints(username, 0, 0);

		password = new Label(" Password : ");
		GridPane.setConstraints(password, 0, 1);

		usernameInput = new TextField();
		usernameInput.setPromptText(" Enter username "); 
		GridPane.setConstraints(usernameInput, 1, 0);

		passwordInput = new PasswordField();
		passwordInput.setPromptText(" Enter Password ");
		GridPane.setConstraints(passwordInput, 1, 1);

		loginButton = new Button(" Login ");
		GridPane.setConstraints(loginButton, 1, 2);

		SignUpButton = new Button(" Sign up ");
		GridPane.setConstraints(SignUpButton, 0, 2);

		ForgotPassword = new Button(" Forgot Passwords");
		GridPane.setConstraints(ForgotPassword, 0, 3);

		grid.getChildren().addAll(username, usernameInput, password, passwordInput, loginButton, SignUpButton,
				ForgotPassword);

		scene = new Scene(grid, 500, 500, Color.AQUA);
		scene.getStylesheets().add("graphicUserInterface/thing.css");

		primaryStage.setScene(scene);
		primaryStage.show();

		SignUpButton.setOnAction(e->{

			Registration reg = new Registration();

			try {
				reg.start(primaryStage);
			} catch (Exception e1) {
		
				e1.printStackTrace();
			}
		});

		loginButton.setOnAction(e -> {
			
			user = usernameInput.getText();
			
			String pw = passwordInput.getText();
			
			loginObject login = new loginObject(user, pw);
			
			if(pw.isEmpty() || user.isEmpty()) {

				AlertBox.display("Blank Field Alert!", "Please enter your username and password");

			}else if (login.executeLogin() == true) {
				
				if(user.contains("Admin")) {
					
					currentAccount = RetrieveDBO.retrieveAccount(user);
					
					currentAccount = new Admin(currentAccount.getAccountID(),currentAccount.getUserName()
							, currentAccount.getPassword(), currentAccount.getFirstName(), currentAccount.getLastName(),
							currentAccount.getAddress(),currentAccount.getState() , currentAccount.getEmail(),
							currentAccount.getZipCode(), currentAccount.getSsn(),currentAccount.getSecurityQuestion(),
							currentAccount.getSecurityAnswer());
					
					
					
					AdminLogin adminScreen = new AdminLogin();
					try {
						adminScreen.start(primaryStage);
				
					}catch(Exception ex1) {
						ex1.printStackTrace();
					}
					
				}else {
					
					currentAccount = RetrieveDBO.retrieveAccount(user);
				
				try {
					MainMenu nextScreen = new MainMenu();
					nextScreen.start(primaryStage);
				} catch (Exception ex) {
					ex.printStackTrace();
				
				}
				}
				
			}else {
				AlertBox.display("Alert!", " Incorrect Password :(");
			}
		});

		ForgotPassword.setOnAction(e -> {

			Passwordrecover pRecovery = new Passwordrecover();
			try {
				pRecovery.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

}