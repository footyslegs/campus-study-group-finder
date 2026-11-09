package edu.westga.comp4420.project1.test.model.temperature_converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.westga.comp4420.project1.model.TemperatureConverter;

class TestToFahrenheit {

	private static final double DELTA = 0.001;

	@ParameterizedTest
	@CsvSource({
		"-273.15, -459.67",
		"-40.0, -40.0",
		"0.0, 32.0",
		"37.0, 98.6",
		"100.0, 212.0"
	})
	void testValidTemperature(double celsius, double expectedFahrenheit) {
		TemperatureConverter converter = new TemperatureConverter();

		double result = converter.toFahrenheit(celsius);

		assertEquals(expectedFahrenheit, result, DELTA, "checking the converted fahrenheit value");
	}

	@Test
	void testBelowAbsoluteZero() {
		TemperatureConverter converter = new TemperatureConverter();

		assertThrows(IllegalArgumentException.class, () -> converter.toFahrenheit(-273.16));
	}
}
