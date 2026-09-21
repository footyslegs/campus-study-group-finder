package edu.westga.comp4420.project1.model;

/** Converts temperatures between the Fahrenheit and Celsius scales.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class TemperatureConverter {

	/** The lowest possible temperature in degrees Celsius. */
	public static final double ABSOLUTE_ZERO_CELSIUS = -273.15;

	/** The lowest possible temperature in degrees Fahrenheit. */
	public static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

	private static final String BELOW_ABSOLUTE_ZERO = "temperature cannot be below absolute zero";

	/** Converts the provided temperature from Fahrenheit to Celsius.
	 *
	 * @precondition fahrenheit >= ABSOLUTE_ZERO_FAHRENHEIT
	 * @postcondition none
	 *
	 * @param fahrenheit the temperature in degrees Fahrenheit
	 *
	 * @return the temperature in degrees Celsius
	 */
	public double toCelsius(double fahrenheit) {
		if (fahrenheit < ABSOLUTE_ZERO_FAHRENHEIT) {
			throw new IllegalArgumentException(BELOW_ABSOLUTE_ZERO);
		}
		return (fahrenheit - 32.0) * 5.0 / 9.0;
	}

	/** Converts the provided temperature from Celsius to Fahrenheit.
	 *
	 * @precondition celsius >= ABSOLUTE_ZERO_CELSIUS
	 * @postcondition none
	 *
	 * @param celsius the temperature in degrees Celsius
	 *
	 * @return the temperature in degrees Fahrenheit
	 */
	public double toFahrenheit(double celsius) {
		if (celsius < ABSOLUTE_ZERO_CELSIUS) {
			throw new IllegalArgumentException(BELOW_ABSOLUTE_ZERO);
		}
		return celsius * 9.0 / 5.0 + 32.0;
	}
}
