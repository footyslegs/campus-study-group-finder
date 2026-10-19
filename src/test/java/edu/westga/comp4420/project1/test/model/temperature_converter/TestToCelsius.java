package edu.westga.comp4420.project1.test.model.temperature_converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.westga.comp4420.project1.model.TemperatureConverter;

class TestToCelsius {

	private static final double DELTA = 0.001;

	@ParameterizedTest
	@CsvSource({
		"-459.67, -273.15",
		"-40.0, -40.0",
		"32.0, 0.0",
		"98.6, 37.0",
		"212.0, 100.0"
	})
	void testValidTemperature(double fahrenheit, double expectedCelsius) {
		TemperatureConverter converter = new TemperatureConverter();

		double result = converter.toCelsius(fahrenheit);

		assertEquals(expectedCelsius, result, DELTA, "checking the converted celsius value");
	}

	@Test
	void testBelowAbsoluteZero() {
		TemperatureConverter converter = new TemperatureConverter();

		assertThrows(IllegalArgumentException.class, () -> converter.toCelsius(-459.68));
	}
}
