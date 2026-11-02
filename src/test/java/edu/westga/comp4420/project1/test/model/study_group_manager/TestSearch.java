package edu.westga.comp4420.project1.test.model.study_group_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.comp4420.project1.model.Student;
import edu.westga.comp4420.project1.model.StudyGroup;
import edu.westga.comp4420.project1.model.StudyGroupManager;

class TestSearch {

	private final Student organizer = new Student("Ada", "1");
	private StudyGroupManager manager;
	private StudyGroup later;
	private StudyGroup sooner;
	private StudyGroup other;

	@BeforeEach
	void setUp() {
		this.manager = new StudyGroupManager();
		this.later = this.manager.createGroup("COMP 4420", "Later", "Library", LocalDateTime.of(2026, 12, 5, 9, 0), 4,
				this.organizer);
		this.sooner = this.manager.createGroup("comp4420", "Sooner", "Lab", LocalDateTime.of(2026, 12, 2, 9, 0), 4,
				this.organizer);
		this.other = this.manager.createGroup("MATH 2000", "Calculus", "Hall", LocalDateTime.of(2026, 12, 3, 9, 0), 4,
				this.organizer);
	}

	@Test
	void testNewManagerIsEmpty() {
		assertTrue(new StudyGroupManager().getGroups().isEmpty(), "no groups yet");
	}

	@Test
	void testCreateGroupAddsIt() {
		assertEquals(3, this.manager.getGroups().size(), "three groups");
		assertSame(this.later, this.manager.getGroups().get(0), "creation order kept");
	}

	@Test
	void testCreateGroupInvalid() {
		assertThrows(IllegalArgumentException.class,
				() -> this.manager.createGroup("bad", "t", "l", LocalDateTime.now(), 4, this.organizer));
		assertEquals(3, this.manager.getGroups().size(), "nothing added");
	}

	@ParameterizedTest
	@ValueSource(strings = {"COMP 4420", "comp4420", "  Comp   4420 ", "comp", "4420"})
	void testSearchMatchesCourse(String text) {
		List<StudyGroup> results = this.manager.search(text);

		assertEquals(List.of(this.sooner, this.later), results, "soonest first");
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "   "})
	void testBlankSearchReturnsAll(String text) {
		assertEquals(List.of(this.sooner, this.other, this.later), this.manager.search(text), "everything");
	}

	@Test
	void testNullSearchReturnsAll() {
		assertEquals(3, this.manager.search(null).size(), "everything");
	}

	@Test
	void testNoMatch() {
		assertTrue(this.manager.search("BIOL").isEmpty(), "nothing matches");
	}
}
