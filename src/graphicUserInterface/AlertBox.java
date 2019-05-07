package graphicUserInterface;

	

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
	
	
	public static void display(String title, String message) {
		
		Stage window = new Stage();
		
		// block events to other window.
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		
		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button("Close this window");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning 
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("graphicUserInterface/thing.css");
		window.setScene(scene);
		window.showAndWait();
		
	

	}

	
		
	}
	
	





