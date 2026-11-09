package edu.westga.comp4420.project1.view.codebehind;

import edu.westga.comp4420.project1.model.StudyGroup;
import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.GroupDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/** Code behind for the group details window. It only wires controls to the view model.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class GroupDetailsWindow {

	@FXML
	private Label headingLabel;

	@FXML
	private Label detailsLabel;

	@FXML
	private Label seatsLabel;

	@FXML
	private ListView<String> memberListView;

	@FXML
	private Button joinButton;

	@FXML
	private Button leaveButton;

	@FXML
	private Label messageLabel;

	private GroupDetailsViewModel viewModel;

	/** Chooses the group to show and binds the controls to it. Called before the window is shown.
	 *
	 * @precondition group != null && a student has signed in on the shared session
	 * @postcondition the window describes the group
	 *
	 * @param group the group to show
	 */
	public void setGroup(StudyGroup group) {
		this.viewModel = new GroupDetailsViewModel(AppSession.getShared(), group);
		this.headingLabel.textProperty().bind(this.viewModel.headingProperty());
		this.detailsLabel.textProperty().bind(this.viewModel.detailsProperty());
		this.seatsLabel.textProperty().bind(this.viewModel.seatsProperty());
		this.messageLabel.textProperty().bind(this.viewModel.messageProperty());
		this.memberListView.setItems(this.viewModel.getMemberNames());
		this.joinButton.disableProperty().bind(this.viewModel.canJoinProperty().not());
		this.leaveButton.disableProperty().bind(this.viewModel.canLeaveProperty().not());
		this.joinButton.setOnAction(event -> this.viewModel.join());
		this.leaveButton.setOnAction(event -> this.viewModel.leave());
	}
}
