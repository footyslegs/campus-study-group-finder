package edu.westga.comp4420.project1.view.codebehind;

import java.io.IOException;

import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.SignInViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Code behind for the sign in window. It only wires controls to the view model.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class SignInWindow {

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField studentIdTextField;

	@FXML
	private Button signInButton;

	@FXML
	private Label messageLabel;

	private final SignInViewModel viewModel;

	/** Creates the code behind along with its view model.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public SignInWindow() {
		this.viewModel = new SignInViewModel(AppSession.getShared());
	}

	@FXML
	void initialize() {
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.nameProperty());
		this.studentIdTextField.textProperty().bindBidirectional(this.viewModel.studentIdProperty());
		this.messageLabel.textProperty().bind(this.viewModel.messageProperty());
		this.signInButton.setOnAction(event -> this.handleSignIn());
	}

	private void handleSignIn() {
		if (!this.viewModel.signIn()) {
			return;
		}
		Stage stage = (Stage) this.signInButton.getScene().getWindow();
		try {
			WindowLauncher.show(WindowLauncher.MAIN_WINDOW, "Study Groups", stage);
		} catch (IOException e) {
			this.messageLabel.textProperty().unbind();
			this.messageLabel.setText("The main window could not be opened.");
		}
	}
}
