package graphicUserInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class SplashScreen extends Application {

	HBox hbox;

	Button letsgo;

	public static void main(String[] args) {

		launch(args);

	}

	@Override

	public void start(Stage stage) throws FileNotFoundException {

		// this next line will create the image.

		  hbox = new HBox();

		  hbox.setPadding(new Insets(5,5,5,5));
		  
		  FadeTransition fadeouttransition = new FadeTransition(Duration.millis(10000), hbox);

		  fadeouttransition.setFromValue(1.0);

		  fadeouttransition.setToValue(0.0);

		  fadeouttransition.setCycleCount(1);
		  //Lambda expression to call the event handler for button action.
		  
		  fadeouttransition.setOnFinished(e-> {

			  Login login = new Login();
			 //try and catch to handle the Exceptions thrown from start(stage) method
			  try {

				  login.start(stage);
				  
			  }catch(Exception e1) {

				  e1.printStackTrace();

			  }

		  });

		  //tell java the file path, package where image is
		Image image = new Image("graphicUserInterface/NightSky.png");
			
		//setting image view

		ImageView imageView = new ImageView(image); 

	

		//Setting the position of the image

		imageView.setX(100);
		imageView.setY(95);

		

		// setting the fit height and width of the image view\

		imageView.setFitHeight(445);
		imageView.setFitWidth(500);

		
		//setting the preserve ratio of the image view



		  Text text1 = new Text(25, 25, "Welcome to J.J Airline ");

	        text1.setFill(Color.ORANGE);

	        text1.setFont(Font.font(java.awt.Font.SERIF, 35));

	        

	        

		imageView.setPreserveRatio(true);

		hbox.getChildren().addAll(imageView, text1);

		//creating group object 
		
		
		//creating scene object

		Scene scene = new Scene(hbox, 700, 550,Color.BLACK);
		scene.getStylesheets().add("graphicUserInterface/thing.css");



		// Setting the title of the stage

		stage.setTitle("Loading Image.....");


		//Adding scene to stage

		stage.setScene(scene);

		stage.show();	

		fadeouttransition.play();

	}
}
