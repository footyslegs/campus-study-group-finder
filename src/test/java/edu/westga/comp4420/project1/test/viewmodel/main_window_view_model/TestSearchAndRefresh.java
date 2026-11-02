package edu.westga.comp4420.project1.test.viewmodel.main_window_view_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.model.Student;
import edu.westga.comp4420.project1.model.StudyGroupManager;
import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.MainWindowViewModel;

class TestSearchAndRefresh {

	private final Student student = new Student("Ada", "1");
	private AppSession session;
	private MainWindowViewModel viewModel;

	@BeforeEach
	void setUp() {
		this.session = new AppSession(new StudyGroupManager());
		this.session.signIn(this.student);
		LocalDateTime time = LocalDateTime.of(2026, 12, 1, 9, 0);
		this.session.getManager().createGroup("COMP 4420", "DevOps", "Library", time, 4, this.student);
		this.session.getManager().createGroup("MATH 2000", "Calculus", "Hall", time.plusDays(1), 4, this.student);
		this.viewModel = new MainWindowViewModel(this.session);
	}

	@Test
	void testConstructorLoadsEverything() {
		assertEquals(2, this.viewModel.getGroups().size(), "all groups listed");
		assertTrue(this.viewModel.searchTextProperty().get().isEmpty(), "no search text");
		assertNull(this.viewModel.selectedGroupProperty().get(), "nothing selected");
		assertSame(this.session, this.viewModel.getSession(), "session kept");
		assertEquals("Signed in as Ada (1)", this.viewModel.welcomeProperty().get(), "welcome text");
	}

	@Test
	void testNullSession() {
		assertThrows(IllegalArgumentException.class, () -> new MainWindowViewModel(null));
	}

	@Test
	void testSessionWithoutStudent() {
		AppSession empty = new AppSession(new StudyGroupManager());

		assertThrows(IllegalArgumentException.class, () -> new MainWindowViewModel(empty));
	}

	@Test
	void testTypingFiltersTheList() {
		this.viewModel.searchTextProperty().set("math");

		assertEquals(1, this.viewModel.getGroups().size(), "one match");
		assertEquals("Calculus", this.viewModel.getGroups().get(0).getTitle(), "the math group");
	}

	@Test
	void testClearingSearchShowsAll() {
		this.viewModel.searchTextProperty().set("math");
		this.viewModel.searchTextProperty().set("");

		assertEquals(2, this.viewModel.getGroups().size(), "all groups again");
	}

	@Test
	void testRefreshPicksUpNewGroup() {
		this.session.getManager().createGroup("COMP 3000", "New", "Lab", LocalDateTime.of(2026, 12, 9, 9, 0), 3,
				this.student);

		this.viewModel.refresh();

		assertEquals(3, this.viewModel.getGroups().size(), "new group listed");
	}
}
