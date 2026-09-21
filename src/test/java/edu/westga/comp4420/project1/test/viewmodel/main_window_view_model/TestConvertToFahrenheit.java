package edu.westga.comp4420.project1.test.viewmodel.main_window_view_model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.viewmodel.MainWindowViewModel;

class TestConvertToFahrenheit {

	@Test
	void testValidInput() {
		MainWindowViewModel viewModel = new MainWindowViewModel();
		viewModel.celsiusProperty().set(" 100 ");

		viewModel.convertToFahrenheit();

		assertEquals("212.00", viewModel.fahrenheitProperty().get(), "checking the converted value");
		assertEquals("", viewModel.messageProperty().get(), "checking that no problem was reported");
	}

	@Test
	void testValidInputClearsOldMessage() {
		MainWindowViewModel viewModel = new MainWindowViewModel();
		viewModel.celsiusProperty().set("cold");
		viewModel.convertToFahrenheit();
		viewModel.celsiusProperty().set("0");

		viewModel.convertToFahrenheit();

		assertEquals("32.00", viewModel.fahrenheitProperty().get(), "checking the converted value");
		assertEquals("", viewModel.messageProperty().get(), "checking that the old message was cleared");
	}

	@Test
	void testNonNumericInput() {
		MainWindowViewModel viewModel = new MainWindowViewModel();
		viewModel.celsiusProperty().set("cold");

		viewModel.convertToFahrenheit();

		assertEquals("", viewModel.fahrenheitProperty().get(), "checking that fahrenheit was left alone");
		assertEquals("Celsius must be a number.", viewModel.messageProperty().get(), "checking the reported problem");
	}

	@Test
	void testBelowAbsoluteZero() {
		MainWindowViewModel viewModel = new MainWindowViewModel();
		viewModel.celsiusProperty().set("-300");

		viewModel.convertToFahrenheit();

		assertEquals("", viewModel.fahrenheitProperty().get(), "checking that fahrenheit was left alone");
		assertEquals("temperature cannot be below absolute zero", viewModel.messageProperty().get(), "checking the reported problem");
	}
}
