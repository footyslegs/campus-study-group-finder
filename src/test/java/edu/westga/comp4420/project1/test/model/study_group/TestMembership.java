package edu.westga.comp4420.project1.test.model.study_group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.model.Student;
import edu.westga.comp4420.project1.model.StudyGroup;

class TestMembership {

	private final Student organizer = new Student("Ada", "1");
	private final Student bob = new Student("Bob", "2");
	private final Student cleo = new Student("Cleo", "3");
	private StudyGroup group;

	@BeforeEach
	void setUp() {
		this.group = new StudyGroup("CS 1301", "t", "l", LocalDateTime.of(2026, 12, 1, 9, 0), 2, this.organizer);
	}

	@Test
	void testAddMember() {
		this.group.addMember(this.bob);

		assertTrue(this.group.isMember(this.bob), "bob joined");
		assertEquals(2, this.group.getMemberCount(), "two members");
		assertTrue(this.group.isFull(), "capacity reached");
		assertEquals(this.bob, this.group.getMembers().get(1), "order kept");
	}

	@Test
	void testAddToFullGroup() {
		this.group.addMember(this.bob);

		assertThrows(IllegalStateException.class, () -> this.group.addMember(this.cleo));
		assertFalse(this.group.isMember(this.cleo), "cleo was not added");
	}

	@Test
	void testAddDuplicate() {
		assertThrows(IllegalStateException.class, () -> this.group.addMember(this.organizer));
	}

	@Test
	void testAddNull() {
		assertThrows(IllegalArgumentException.class, () -> this.group.addMember(null));
	}

	@Test
	void testRemoveMember() {
		this.group.addMember(this.bob);

		this.group.removeMember(this.bob);

		assertFalse(this.group.isMember(this.bob), "bob left");
		assertFalse(this.group.isFull(), "seat freed");
	}

	@Test
	void testRemoveNonMember() {
		assertThrows(IllegalStateException.class, () -> this.group.removeMember(this.bob));
	}

	@Test
	void testOrganizerCannotLeave() {
		assertThrows(IllegalStateException.class, () -> this.group.removeMember(this.organizer));
	}

	@Test
	void testRemoveNull() {
		assertThrows(IllegalArgumentException.class, () -> this.group.removeMember(null));
	}

	@Test
	void testMembersViewIsReadOnly() {
		assertThrows(UnsupportedOperationException.class, () -> this.group.getMembers().add(this.bob));
	}
}
