package edu.westga.comp4420.project1.test.viewmodel.app_session;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.project1.model.Student;
import edu.westga.comp4420.project1.model.StudyGroupManager;
import edu.westga.comp4420.project1.viewmodel.AppSession;

class TestAppSession {

	@Test
	void testNewSessionHasNoStudent() {
		StudyGroupManager manager = new StudyGroupManager();
		AppSession session = new AppSession(manager);

		assertSame(manager, session.getManager(), "manager kept");
		assertNull(session.getCurrentStudent(), "nobody signed in");
	}

	@Test
	void testNullManager() {
		assertThrows(IllegalArgumentException.class, () -> new AppSession(null));
	}

	@Test
	void testSignIn() {
		AppSession session = new AppSession(new StudyGroupManager());
		Student student = new Student("Ada", "1");

		session.signIn(student);

		assertSame(student, session.getCurrentStudent(), "student signed in");
	}

	@Test
	void testSignInNull() {
		AppSession session = new AppSession(new StudyGroupManager());

		assertThrows(IllegalArgumentException.class, () -> session.signIn(null));
	}

	@Test
	void testSharedSession() {
		assertNotNull(AppSession.getShared(), "shared session exists");
		assertSame(AppSession.getShared(), AppSession.getShared(), "same instance every time");
	}
}
