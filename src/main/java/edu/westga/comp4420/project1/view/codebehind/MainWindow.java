package edu.westga.comp4420.project1.view.codebehind;

import edu.westga.comp4420.project1.model.StudyGroup;
import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.MainWindowViewModel;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Code behind for the main window. It only wires controls to the view model.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class MainWindow {

	@FXML
	private Label welcomeLabel;

	@FXML
	private TextField searchTextField;

	@FXML
	private Button createGroupButton;

	@FXML
	private ListView<StudyGroup> groupListView;

	private final MainWindowViewModel viewModel;

	/** Creates the code behind along with its view model.
	 *
	 * @precondition a student has signed in on the shared session
	 * @postcondition none
	 */
	public MainWindow() {
		this.viewModel = new MainWindowViewModel(AppSession.getShared());
	}

	@FXML
	void initialize() {
		this.welcomeLabel.textProperty().bind(this.viewModel.welcomeProperty());
		this.searchTextField.textProperty().bindBidirectional(this.viewModel.searchTextProperty());
		this.groupListView.setItems(this.viewModel.getGroups());
		this.viewModel.selectedGroupProperty().bind(this.groupListView.getSelectionModel().selectedItemProperty());
		this.createGroupButton.setOnAction(event -> this.openCreateGroupWindow());
	}

	private void openCreateGroupWindow() {
		Stage owner = (Stage) this.createGroupButton.getScene().getWindow();
		try {
			WindowLauncher.showModal(WindowLauncher.CREATE_GROUP_WINDOW, "Create Group", owner, controller -> { });
		} catch (IOException e) {
			this.welcomeLabel.textProperty().unbind();
			this.welcomeLabel.setText("The Create Group window could not be opened.");
		}
		this.viewModel.refresh();
	}
}
