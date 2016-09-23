package com.starterkit.javafx;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application entry point.
 *
 * @author Daniel
 */
public class Startup extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	/**
	 * @see {@link javafx.application.Application#start(Stage)}
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("StarterKit-JavaFX");

		/*
		 * Load screen from FXML file with specific language bundle (derived
		 * from default locale).
		 */
		Parent root = FXMLLoader.load(getClass().getResource("/com/starterkit/javafx/view/userProfile-search.fxml"), //
				ResourceBundle.getBundle("com/starterkit/javafx/bundle/base"));
		
		Scene scene = new Scene(root);

		/*
		 * Set the style sheet(s) for application.
		 */
		scene.getStylesheets().add(getClass().getResource("/com/starterkit/javafx/css/standard.css").toExternalForm());

		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
}
