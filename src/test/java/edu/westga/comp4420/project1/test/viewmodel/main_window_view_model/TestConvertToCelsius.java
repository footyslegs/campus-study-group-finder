package edu.westga.comp4420.project1.test.viewmodel.main_window_view_model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.viewmodel.MainWindowViewModel;

class TestConvertToCelsius {

	@Test
	void testValidInput() {
		MainWindowViewModel viewModel = new MainWindowViewModel();
		viewModel.fahrenheitProperty().set(" 212 ");

		viewModel.convertToCelsius();

		assertEquals("100.00", viewModel.celsiusProperty().get(), "checking the converted value");
		assertEquals("", viewModel.messageProperty().get(), "checking that no problem was reported");
	}

	@Test
	void testValidInputClearsOldMessage() {
		MainWindowViewModel viewModel = new MainWindowViewModel();
		viewModel.fahrenheitProperty().set("hot");
		viewModel.convertToCelsius();
		viewModel.fahrenheitProperty().set("32");

		viewModel.convertToCelsius();

		assertEquals("0.00", viewModel.celsiusProperty().get(), "checking the converted value");
		assertEquals("", viewModel.messageProperty().get(), "checking that the old message was cleared");
	}

	@Test
	void testNonNumericInput() {
		MainWindowViewModel viewModel = new MainWindowViewModel();
		viewModel.fahrenheitProperty().set("hot");

		viewModel.convertToCelsius();

		assertEquals("", viewModel.celsiusProperty().get(), "checking that celsius was left alone");
		assertEquals("Fahrenheit must be a number.", viewModel.messageProperty().get(), "checking the reported problem");
	}

	@Test
	void testBelowAbsoluteZero() {
		MainWindowViewModel viewModel = new MainWindowViewModel();
		viewModel.fahrenheitProperty().set("-500");

		viewModel.convertToCelsius();

		assertEquals("", viewModel.celsiusProperty().get(), "checking that celsius was left alone");
		assertEquals("temperature cannot be below absolute zero", viewModel.messageProperty().get(), "checking the reported problem");
	}
}
