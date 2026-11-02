package edu.westga.comp4420.project1.test.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.model.Student;

class TestEqualsAndToString {

	@Test
	void testSameObject() {
		Student student = new Student("Ada", "1");

		assertTrue(student.equals(student), "same object is equal");
	}

	@Test
	void testSameIdIgnoringCase() {
		Student first = new Student("Ada", "abc1");
		Student second = new Student("Different Name", "ABC1");

		assertEquals(first, second, "same ID means same student");
		assertEquals(first.hashCode(), second.hashCode(), "hash codes agree");
	}

	@Test
	void testDifferentId() {
		assertNotEquals(new Student("Ada", "1"), new Student("Ada", "2"), "different IDs");
	}

	@Test
	void testNotAStudent() {
		assertFalse(new Student("Ada", "1").equals("1"), "a string is not a student");
		assertFalse(new Student("Ada", "1").equals(null), "null is not a student");
	}

	@Test
	void testToString() {
		assertEquals("Ada (1)", new Student("Ada", "1").toString(), "checking text");
	}
}
