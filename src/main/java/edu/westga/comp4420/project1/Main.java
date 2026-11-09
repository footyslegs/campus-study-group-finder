package edu.westga.comp4420.project1;

import java.io.IOException;

import edu.westga.comp4420.project1.view.codebehind.WindowLauncher;
import javafx.application.Application;
import javafx.stage.Stage;

/** Entry point for the Campus Study Group Finder. It opens the sign in window first.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class Main extends Application {

	/** Shows the sign in window.
	 *
	 * @precondition primaryStage != null
	 * @postcondition the sign in window is visible
	 *
	 * @param primaryStage the stage provided by JavaFX
	 *
	 * @throws IOException if the window resource cannot be loaded
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		WindowLauncher.show(WindowLauncher.SIGN_IN_WINDOW, "Sign In", primaryStage);
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
