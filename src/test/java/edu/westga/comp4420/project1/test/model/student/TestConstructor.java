package edu.westga.comp4420.project1.test.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.comp4420.project1.model.Student;

class TestConstructor {

	@Test
	void testValidStudentTrimsFields() {
		Student student = new Student("  Ada Lovelace ", " 900123 ");

		assertEquals("Ada Lovelace", student.getName(), "checking name");
		assertEquals("900123", student.getStudentId(), "checking ID");
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"   "})
	void testBlankName(String name) {
		assertThrows(IllegalArgumentException.class, () -> new Student(name, "900123"));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"   "})
	void testBlankId(String studentId) {
		assertThrows(IllegalArgumentException.class, () -> new Student("Ada", studentId));
	}
}
