package edu.westga.comp4420.project1.test.model.course_code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.comp4420.project1.model.CourseCode;

class TestCourseCode {

	@ParameterizedTest
	@ValueSource(strings = {"COMP 4420", "comp4420", " cs 1301 ", "MATH2000"})
	void testValidCodes(String text) {
		assertTrue(CourseCode.isValid(text), "checking valid code");
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"C 4420", "COMPUTER 4420", "COMP 442", "COMP 44200", "4420 COMP", "COMP-4420"})
	void testInvalidCodes(String text) {
		assertFalse(CourseCode.isValid(text), "checking invalid code");
	}

	@ParameterizedTest
	@CsvSource({"comp4420, COMP 4420", "' cs   1301', CS 1301", "Math 2000, MATH 2000"})
	void testNormalize(String text, String expected) {
		assertEquals(expected, CourseCode.normalize(text), "checking canonical form");
	}

	@Test
	void testNormalizeInvalid() {
		assertThrows(IllegalArgumentException.class, () -> CourseCode.normalize("nope"));
	}
}
