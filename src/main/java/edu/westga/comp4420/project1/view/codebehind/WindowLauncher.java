package edu.westga.comp4420.project1.view.codebehind;

import java.io.IOException;
import java.util.function.Consumer;

import edu.westga.comp4420.project1.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Loads FXML windows and shows them on a stage.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public final class WindowLauncher {

	/** Resource name of the sign in window. */
	public static final String SIGN_IN_WINDOW = "view/SignInWindow.fxml";

	/** Resource name of the Create Group window. */
	public static final String CREATE_GROUP_WINDOW = "view/CreateGroupWindow.fxml";

	/** Resource name of the main window. */
	public static final String MAIN_WINDOW = "view/MainWindow.fxml";

	private WindowLauncher() {
		// utility class, not meant to be instantiated
	}

	/** Loads a window and shows it on the given stage.
	 *
	 * @precondition resource != null && title != null && stage != null
	 * @postcondition the window is visible on the stage
	 *
	 * @param resource the FXML resource name
	 * @param title the window title
	 * @param stage the stage to show the window on
	 *
	 * @return the controller of the loaded window
	 *
	 * @throws IOException if the resource cannot be loaded
	 */
	public static Object show(String resource, String title, Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(resource));
		Parent parent = loader.load();
		stage.setTitle(title);
		stage.setScene(new Scene(parent));
		stage.show();
		return loader.getController();
	}

	/** Loads a window and shows it in a new modal stage, returning once it is closed.
	 *
	 * @precondition resource != null && title != null && owner != null
	 * @postcondition the window has been closed
	 *
	 * @param resource the FXML resource name
	 * @param title the window title
	 * @param owner the stage that is blocked while the new window is open
	 * @param setup receives the controller before the window is shown, so it can be given its data
	 *
	 * @throws IOException if the resource cannot be loaded
	 */
	public static void showModal(String resource, String title, Stage owner, Consumer<Object> setup)
			throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(resource));
		Parent parent = loader.load();
		setup.accept(loader.getController());
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle(title);
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
}
