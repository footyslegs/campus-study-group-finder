package edu.westga.comp4420.project1.viewmodel;

import edu.westga.comp4420.project1.model.TemperatureConverter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** Manages the state of the main window and connects it to the model.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class MainWindowViewModel {

	private static final String RESULT_FORMAT = "%.2f";

	private final TemperatureConverter converter;
	private final StringProperty fahrenheit;
	private final StringProperty celsius;
	private final StringProperty message;

	/** Creates a new view model with empty inputs.
	 *
	 * @precondition none
	 * @postcondition fahrenheitProperty().get().isEmpty() && celsiusProperty().get().isEmpty()
	 * 					&& messageProperty().get().isEmpty()
	 */
	public MainWindowViewModel() {
		this.converter = new TemperatureConverter();
		this.fahrenheit = new SimpleStringProperty("");
		this.celsius = new SimpleStringProperty("");
		this.message = new SimpleStringProperty("");
	}

	/** Gets the fahrenheit property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the fahrenheit property
	 */
	public StringProperty fahrenheitProperty() {
		return this.fahrenheit;
	}

	/** Gets the celsius property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the celsius property
	 */
	public StringProperty celsiusProperty() {
		return this.celsius;
	}

	/** Gets the message property, which describes the last problem found (if any).
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the message property
	 */
	public StringProperty messageProperty() {
		return this.message;
	}

	/** Converts the fahrenheit input and stores the result in the celsius property.
	 *
	 * @precondition none
	 * @postcondition celsiusProperty() holds the converted value if the input was valid,
	 * 					otherwise messageProperty() describes the problem
	 */
	public void convertToCelsius() {
		try {
			double input = Double.parseDouble(this.fahrenheit.get().trim());
			double result = this.converter.toCelsius(input);
			this.celsius.set(String.format(RESULT_FORMAT, result));
			this.message.set("");
		} catch (NumberFormatException e) {
			this.message.set("Fahrenheit must be a number.");
		} catch (IllegalArgumentException e) {
			this.message.set(e.getMessage());
		}
	}

	/** Converts the celsius input and stores the result in the fahrenheit property.
	 *
	 * @precondition none
	 * @postcondition fahrenheitProperty() holds the converted value if the input was valid,
	 * 					otherwise messageProperty() describes the problem
	 */
	public void convertToFahrenheit() {
		try {
			double input = Double.parseDouble(this.celsius.get().trim());
			double result = this.converter.toFahrenheit(input);
			this.fahrenheit.set(String.format(RESULT_FORMAT, result));
			this.message.set("");
		} catch (NumberFormatException e) {
			this.message.set("Celsius must be a number.");
		} catch (IllegalArgumentException e) {
			this.message.set(e.getMessage());
		}
	}
}
