package graphicUserInterface;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import databaseInterfaceLayer.LoginDBO;

public class Passwordrecover extends Application {
    
	
	GridPane grid;
	Scene scene;
	Label email;
	TextField emailInput;
	Label emailConfirm;
	TextField emailConfirmInput;
	Label SecurityQuestion;
	Label SecurityAnswer;
	TextField SecurityAnswerInput;
	Button PasswordRetreat;
	Button backButton; 
	Label displayPassword;
	Label wrongPassword;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		email = new Label(" Enter your email ");
		GridPane.setConstraints(email, 0, 0);
		emailInput = new TextField();
		emailInput.setPromptText(" Email: ");
		GridPane.setConstraints(emailInput, 0, 1);
		
		emailConfirm = new Label(" Confrim your email ");
		GridPane.setConstraints(emailConfirm, 0, 2);
		emailConfirmInput = new TextField();
		emailConfirmInput.setPromptText(" Email: ");
		GridPane.setConstraints(emailConfirmInput, 0, 3);
		
		SecurityQuestion = new Label(" Choose Security Question");
		GridPane.setConstraints(SecurityQuestion, 0, 4);
		
		ChoiceBox<String> choicebox = new ChoiceBox<>();
		GridPane.setConstraints(choicebox, 0, 5);
		
		choicebox.getItems().add(" What is your country Origin ");
		choicebox.getItems().add(" What is your mother middle name ");
		choicebox.getItems().add(" What is your first Elemenatry School ");
		choicebox.getItems().add(" What is your pet name  ");
	
		SecurityAnswer = new Label(" Enter your Answer ");
		GridPane.setConstraints(SecurityAnswer, 0, 6);
		SecurityAnswerInput = new TextField();
		SecurityAnswerInput.setPromptText(" Answer:");
		GridPane.setConstraints(SecurityAnswerInput, 0, 7);

	    PasswordRetreat = new Button(" Retrieve Password ");
		GridPane.setConstraints(PasswordRetreat, 0, 8);
		
		backButton = new Button(" <-Back ");
		GridPane.setConstraints(backButton, 0, 9);
		
		displayPassword = new Label();
		GridPane.setConstraints(displayPassword, 1, 0);

	    
		grid.getChildren().addAll(email, emailInput, emailConfirm, emailConfirmInput, SecurityQuestion,
				                  choicebox,SecurityAnswer, SecurityAnswerInput, PasswordRetreat, backButton);
		
		scene = new Scene(grid, 600,600);
		primaryStage.setTitle("Password Recover");
		scene.getStylesheets().add("graphicUserInterface/thing.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		PasswordRetreat.setOnAction(e->{
			
			String emailOne = emailInput.getText();
			String emailTwo = emailConfirmInput.getText();
			String Ans = SecurityAnswerInput.getText();
			
			
			try {
				
				checkMatch(emailOne,emailTwo);
				
				LoginDBO check = new LoginDBO();
				
				String display = check.returnPassword(emailOne,Ans);
				
				
				AlertBox.display("Attention", display);
			
			
			}catch(Exception ex) {
				
				AlertBox.display("Alert!", "The email address do not match :(");
			}
			
		});
		
		backButton.setOnAction(e->{ 
			Login login = new Login();
			try {
				login.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	public void checkMatch(String email,String email2) throws Exception {
		
		  int i = email.compareTo(email2);
		  
		  if(i > 0 || i < 0) {
			  throw new Exception("Email address don't match");
		  }
		}

}