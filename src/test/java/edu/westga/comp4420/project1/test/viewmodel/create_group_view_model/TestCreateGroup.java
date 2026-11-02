package edu.westga.comp4420.project1.test.viewmodel.create_group_view_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.model.Student;
import edu.westga.comp4420.project1.model.StudyGroup;
import edu.westga.comp4420.project1.model.StudyGroupManager;
import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.CreateGroupViewModel;

class TestCreateGroup {

	private AppSession session;
	private CreateGroupViewModel viewModel;

	@BeforeEach
	void setUp() {
		this.session = new AppSession(new StudyGroupManager());
		this.session.signIn(new Student("Ada", "1"));
		this.viewModel = new CreateGroupViewModel(this.session);
	}

	private void fillValidInputs() {
		this.viewModel.courseCodeProperty().set("comp4420");
		this.viewModel.titleProperty().set("DevOps review");
		this.viewModel.locationProperty().set("Library 2F");
		this.viewModel.meetingTimeProperty().set("2026-12-01 18:30");
		this.viewModel.capacityProperty().set("5");
	}

	@Test
	void testConstructorStartsEmpty() {
		assertEquals("", this.viewModel.courseCodeProperty().get(), "course empty");
		assertEquals("", this.viewModel.titleProperty().get(), "title empty");
		assertEquals("", this.viewModel.locationProperty().get(), "location empty");
		assertEquals("", this.viewModel.meetingTimeProperty().get(), "time empty");
		assertEquals("", this.viewModel.capacityProperty().get(), "capacity empty");
		assertEquals("", this.viewModel.messageProperty().get(), "message empty");
	}

	@Test
	void testNullSession() {
		assertThrows(IllegalArgumentException.class, () -> new CreateGroupViewModel(null));
	}

	@Test
	void testSessionWithoutStudent() {
		AppSession empty = new AppSession(new StudyGroupManager());

		assertThrows(IllegalArgumentException.class, () -> new CreateGroupViewModel(empty));
	}

	@Test
	void testValidGroup() {
		this.fillValidInputs();

		assertTrue(this.viewModel.createGroup(), "group created");
		StudyGroup group = this.session.getManager().getGroups().get(0);
		assertEquals("COMP 4420", group.getCourseCode(), "course");
		assertEquals(5, group.getCapacity(), "capacity");
		assertEquals("Ada", group.getOrganizer().getName(), "organizer is the signed in student");
		assertEquals("", this.viewModel.messageProperty().get(), "no message");
	}

	@Test
	void testCapacityNotANumber() {
		this.fillValidInputs();
		this.viewModel.capacityProperty().set("lots");

		assertFalse(this.viewModel.createGroup(), "creation fails");
		assertEquals("Capacity must be a whole number.", this.viewModel.messageProperty().get(), "message");
		assertTrue(this.session.getManager().getGroups().isEmpty(), "nothing added");
	}

	@Test
	void testBadMeetingTime() {
		this.fillValidInputs();
		this.viewModel.meetingTimeProperty().set("tomorrow");

		assertFalse(this.viewModel.createGroup(), "creation fails");
		assertTrue(this.viewModel.messageProperty().get().startsWith("Meeting time"), "time message");
	}

	@Test
	void testBadCourse() {
		this.fillValidInputs();
		this.viewModel.courseCodeProperty().set("nope");

		assertFalse(this.viewModel.createGroup(), "creation fails");
		assertTrue(this.viewModel.messageProperty().get().contains("course code"), "course message");
	}

	@Test
	void testCapacityOutOfRange() {
		this.fillValidInputs();
		this.viewModel.capacityProperty().set("1");

		assertFalse(this.viewModel.createGroup(), "creation fails");
		assertTrue(this.viewModel.messageProperty().get().contains("capacity"), "capacity message");
	}

	@Test
	void testBlankTitle() {
		this.fillValidInputs();
		this.viewModel.titleProperty().set(" ");

		assertFalse(this.viewModel.createGroup(), "creation fails");
		assertTrue(this.viewModel.messageProperty().get().contains("title"), "title message");
	}
}
