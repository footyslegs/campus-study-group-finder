package edu.westga.comp4420.project1.view.codebehind;

import edu.westga.comp4420.project1.model.StudyGroup;
import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
	}
}
