package edu.westga.comp4420.project1.test.model.study_group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.comp4420.project1.model.StudyGroup;
import edu.westga.comp4420.project1.model.Student;

class TestConstructor {

	private static final LocalDateTime TIME = LocalDateTime.of(2026, 12, 1, 18, 30);
	private final Student organizer = new Student("Ada", "1");

	@Test
	void testValidGroup() {
		StudyGroup group = new StudyGroup("comp4420", " Exam prep ", " Library 2F ", TIME, 5, this.organizer);

		assertEquals("COMP 4420", group.getCourseCode(), "course code normalized");
		assertEquals("Exam prep", group.getTitle(), "title trimmed");
		assertEquals("Library 2F", group.getLocation(), "location trimmed");
		assertEquals(TIME, group.getMeetingTime(), "meeting time");
		assertEquals(5, group.getCapacity(), "capacity");
		assertEquals(this.organizer, group.getOrganizer(), "organizer");
		assertEquals(1, group.getMemberCount(), "organizer is first member");
		assertTrue(group.isMember(this.organizer), "organizer is a member");
		assertFalse(group.isFull(), "not full");
		assertEquals("COMP 4420 - Exam prep", group.toString(), "checking text");
	}

	@Test
	void testBoundaryCapacities() {
		new StudyGroup("CS 1301", "t", "l", TIME, StudyGroup.MINIMUM_CAPACITY, this.organizer);
		new StudyGroup("CS 1301", "t", "l", TIME, StudyGroup.MAXIMUM_CAPACITY, this.organizer);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 1, 51})
	void testCapacityOutOfRange(int capacity) {
		assertThrows(IllegalArgumentException.class,
				() -> new StudyGroup("CS 1301", "t", "l", TIME, capacity, this.organizer));
	}

	@Test
	void testInvalidCourse() {
		assertThrows(IllegalArgumentException.class,
				() -> new StudyGroup("bad", "t", "l", TIME, 5, this.organizer));
	}

	@Test
	void testBlankOrNullTitle() {
		assertThrows(IllegalArgumentException.class,
				() -> new StudyGroup("CS 1301", " ", "l", TIME, 5, this.organizer));
		assertThrows(IllegalArgumentException.class,
				() -> new StudyGroup("CS 1301", null, "l", TIME, 5, this.organizer));
	}

	@Test
	void testBlankOrNullLocation() {
		assertThrows(IllegalArgumentException.class,
				() -> new StudyGroup("CS 1301", "t", "", TIME, 5, this.organizer));
		assertThrows(IllegalArgumentException.class,
				() -> new StudyGroup("CS 1301", "t", null, TIME, 5, this.organizer));
	}

	@Test
	void testNullMeetingTime() {
		assertThrows(IllegalArgumentException.class,
				() -> new StudyGroup("CS 1301", "t", "l", null, 5, this.organizer));
	}

	@Test
	void testNullOrganizer() {
		assertThrows(IllegalArgumentException.class,
				() -> new StudyGroup("CS 1301", "t", "l", TIME, 5, null));
	}
}
