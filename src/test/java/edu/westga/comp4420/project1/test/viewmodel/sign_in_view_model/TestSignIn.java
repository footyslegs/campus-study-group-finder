package edu.westga.comp4420.project1.test.viewmodel.sign_in_view_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.model.StudyGroupManager;
import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.SignInViewModel;

class TestSignIn {

	private AppSession session;
	private SignInViewModel viewModel;

	@BeforeEach
	void setUp() {
		this.session = new AppSession(new StudyGroupManager());
		this.viewModel = new SignInViewModel(this.session);
	}

	@Test
	void testConstructorStartsEmpty() {
		assertEquals("", this.viewModel.nameProperty().get(), "name empty");
		assertEquals("", this.viewModel.studentIdProperty().get(), "ID empty");
		assertEquals("", this.viewModel.messageProperty().get(), "message empty");
	}

	@Test
	void testNullSession() {
		assertThrows(IllegalArgumentException.class, () -> new SignInViewModel(null));
	}

	@Test
	void testValidSignIn() {
		this.viewModel.nameProperty().set("Ada Lovelace");
		this.viewModel.studentIdProperty().set("900123");

		assertTrue(this.viewModel.signIn(), "sign in succeeds");
		assertEquals("Ada Lovelace", this.session.getCurrentStudent().getName(), "student name");
		assertEquals("", this.viewModel.messageProperty().get(), "no message");
	}

	@Test
	void testMissingName() {
		this.viewModel.studentIdProperty().set("900123");

		assertFalse(this.viewModel.signIn(), "sign in fails");
		assertNull(this.session.getCurrentStudent(), "nobody signed in");
		assertFalse(this.viewModel.messageProperty().get().isEmpty(), "problem described");
	}

	@Test
	void testMissingId() {
		this.viewModel.nameProperty().set("Ada");

		assertFalse(this.viewModel.signIn(), "sign in fails");
		assertFalse(this.viewModel.messageProperty().get().isEmpty(), "problem described");
	}

	@Test
	void testMessageClearsAfterSuccess() {
		this.viewModel.signIn();
		this.viewModel.nameProperty().set("Ada");
		this.viewModel.studentIdProperty().set("1");

		assertTrue(this.viewModel.signIn(), "second attempt succeeds");
		assertEquals("", this.viewModel.messageProperty().get(), "message cleared");
	}
}
