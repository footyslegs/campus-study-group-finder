package edu.westga.comp4420.project1.test.viewmodel.group_details_view_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.model.Student;
import edu.westga.comp4420.project1.model.StudyGroup;
import edu.westga.comp4420.project1.model.StudyGroupManager;
import edu.westga.comp4420.project1.viewmodel.AppSession;
import edu.westga.comp4420.project1.viewmodel.GroupDetailsViewModel;

class TestJoinAndLeave {

	private final Student organizer = new Student("Ada", "1");
	private final Student bob = new Student("Bob", "2");
	private AppSession session;
	private StudyGroup group;

	@BeforeEach
	void setUp() {
		this.session = new AppSession(new StudyGroupManager());
		this.session.signIn(this.bob);
		this.group = this.session.getManager().createGroup("COMP 4420", "DevOps", "Library",
				LocalDateTime.of(2026, 12, 1, 18, 30), 2, this.organizer);
	}

	@Test
	void testConstructorDescribesGroup() {
		GroupDetailsViewModel viewModel = new GroupDetailsViewModel(this.session, this.group);

		assertEquals("COMP 4420: DevOps", viewModel.headingProperty().get(), "heading");
		assertEquals("Library, 2026-12-01 18:30", viewModel.detailsProperty().get(), "details");
		assertEquals("1 of 2 seats taken", viewModel.seatsProperty().get(), "seats");
		assertEquals("", viewModel.messageProperty().get(), "message");
		assertTrue(viewModel.canJoinProperty().get(), "bob may join");
		assertFalse(viewModel.canLeaveProperty().get(), "bob may not leave");
		assertEquals(1, viewModel.getMemberNames().size(), "one member listed");
	}

	@Test
	void testInvalidArguments() {
		assertThrows(IllegalArgumentException.class, () -> new GroupDetailsViewModel(null, this.group));
		assertThrows(IllegalArgumentException.class, () -> new GroupDetailsViewModel(this.session, null));
		AppSession empty = new AppSession(new StudyGroupManager());
		assertThrows(IllegalArgumentException.class, () -> new GroupDetailsViewModel(empty, this.group));
	}

	@Test
	void testJoin() {
		GroupDetailsViewModel viewModel = new GroupDetailsViewModel(this.session, this.group);

		viewModel.join();

		assertTrue(this.group.isMember(this.bob), "bob joined");
		assertEquals("You joined the group.", viewModel.messageProperty().get(), "message");
		assertEquals("2 of 2 seats taken", viewModel.seatsProperty().get(), "seats updated");
		assertFalse(viewModel.canJoinProperty().get(), "cannot join twice");
		assertTrue(viewModel.canLeaveProperty().get(), "can leave now");
		assertEquals(2, viewModel.getMemberNames().size(), "two members listed");
	}

	@Test
	void testJoinFullGroup() {
		GroupDetailsViewModel viewModel = new GroupDetailsViewModel(this.session, this.group);
		this.group.addMember(new Student("Cleo", "3"));

		viewModel.join();

		assertFalse(this.group.isMember(this.bob), "bob was not added");
		assertEquals("group is full", viewModel.messageProperty().get(), "message");
	}

	@Test
	void testCannotJoinWhenFull() {
		this.group.addMember(new Student("Cleo", "3"));

		GroupDetailsViewModel viewModel = new GroupDetailsViewModel(this.session, this.group);

		assertFalse(viewModel.canJoinProperty().get(), "join disabled");
	}

	@Test
	void testLeave() {
		GroupDetailsViewModel viewModel = new GroupDetailsViewModel(this.session, this.group);
		viewModel.join();

		viewModel.leave();

		assertFalse(this.group.isMember(this.bob), "bob left");
		assertEquals("You left the group.", viewModel.messageProperty().get(), "message");
		assertTrue(viewModel.canJoinProperty().get(), "can rejoin");
	}

	@Test
	void testLeaveWithoutJoining() {
		GroupDetailsViewModel viewModel = new GroupDetailsViewModel(this.session, this.group);

		viewModel.leave();

		assertEquals("student is not a member of this group", viewModel.messageProperty().get(), "message");
	}

	@Test
	void testOrganizerCannotLeave() {
		this.session.signIn(this.organizer);
		GroupDetailsViewModel viewModel = new GroupDetailsViewModel(this.session, this.group);

		assertFalse(viewModel.canLeaveProperty().get(), "leave disabled");
		assertFalse(viewModel.canJoinProperty().get(), "join disabled");
		viewModel.leave();

		assertEquals("the organizer cannot leave their own group", viewModel.messageProperty().get(), "message");
		assertTrue(this.group.isMember(this.organizer), "organizer stays");
	}

	@Test
	void testJoinAlreadyMember() {
		this.session.signIn(this.organizer);
		GroupDetailsViewModel viewModel = new GroupDetailsViewModel(this.session, this.group);

		viewModel.join();

		assertEquals("student is already a member of this group", viewModel.messageProperty().get(), "message");
	}
}
