package edu.westga.comp4420.project1.test.viewmodel.main_window_view_model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.viewmodel.MainWindowViewModel;

class TestConstructor {

	@Test
	void testStartsWithEmptyProperties() {
		MainWindowViewModel viewModel = new MainWindowViewModel();

		assertEquals("", viewModel.fahrenheitProperty().get(), "checking the fahrenheit property");
		assertEquals("", viewModel.celsiusProperty().get(), "checking the celsius property");
		assertEquals("", viewModel.messageProperty().get(), "checking the message property");
	}
}
