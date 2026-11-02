package edu.westga.comp4420.project1.view.codebehind;

import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.CreateGroupViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Code behind for the Create Group window. It only wires controls to the view model.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class CreateGroupWindow {

	@FXML
	private TextField courseTextField;

	@FXML
	private TextField titleTextField;

	@FXML
	private TextField locationTextField;

	@FXML
	private TextField meetingTimeTextField;

	@FXML
	private TextField capacityTextField;

	@FXML
	private Button createButton;

	@FXML
	private Label messageLabel;

	private final CreateGroupViewModel viewModel;

	/** Creates the code behind along with its view model.
	 *
	 * @precondition a student has signed in on the shared session
	 * @postcondition none
	 */
	public CreateGroupWindow() {
		this.viewModel = new CreateGroupViewModel(AppSession.getShared());
	}

	@FXML
	void initialize() {
		this.courseTextField.textProperty().bindBidirectional(this.viewModel.courseCodeProperty());
		this.titleTextField.textProperty().bindBidirectional(this.viewModel.titleProperty());
		this.locationTextField.textProperty().bindBidirectional(this.viewModel.locationProperty());
		this.meetingTimeTextField.textProperty().bindBidirectional(this.viewModel.meetingTimeProperty());
		this.capacityTextField.textProperty().bindBidirectional(this.viewModel.capacityProperty());
		this.messageLabel.textProperty().bind(this.viewModel.messageProperty());
		this.createButton.setOnAction(event -> {
			if (this.viewModel.createGroup()) {
				((Stage) this.createButton.getScene().getWindow()).close();
			}
		});
	}
}
