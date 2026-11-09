package edu.westga.comp4420.project1.view.codebehind;

import edu.westga.comp4420.project1.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** Code behind for the main window of the application.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class MainWindow {

	@FXML
	private TextField fahrenheitTextField;

	@FXML
	private TextField celsiusTextField;

	@FXML
	private Button toCelsiusButton;

	@FXML
	private Button toFahrenheitButton;

	@FXML
	private Label messageLabel;

	private final MainWindowViewModel viewModel;

	/** Creates the code behind along with its view model.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public MainWindow() {
		this.viewModel = new MainWindowViewModel();
	}

	@FXML
	void initialize() {
		this.fahrenheitTextField.textProperty().bindBidirectional(this.viewModel.fahrenheitProperty());
		this.celsiusTextField.textProperty().bindBidirectional(this.viewModel.celsiusProperty());
		this.messageLabel.textProperty().bind(this.viewModel.messageProperty());

		this.toCelsiusButton.setOnAction(event -> this.viewModel.convertToCelsius());
		this.toFahrenheitButton.setOnAction(event -> this.viewModel.convertToFahrenheit());
	}
}
