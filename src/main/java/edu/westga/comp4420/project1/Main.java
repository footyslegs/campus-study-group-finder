package edu.westga.comp4420.project1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Entry point for the JavaFX application.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class Main extends Application {

	private static final String WINDOW_TITLE = "Temperature Converter";
	private static final String GUI_RESOURCE = "view/MainWindow.fxml";

	/** Loads the main window and shows it.
	 *
	 * @precondition primaryStage != null
	 * @postcondition the main window is visible
	 *
	 * @param primaryStage the stage provided by JavaFX
	 *
	 * @throws IOException if the GUI resource cannot be loaded
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent parent = FXMLLoader.load(Main.class.getResource(GUI_RESOURCE));
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}

	/** Launches the application.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		Main.launch(args);
	}
}
