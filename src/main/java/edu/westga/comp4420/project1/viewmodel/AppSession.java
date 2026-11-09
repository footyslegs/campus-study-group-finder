package edu.westga.comp4420.project1.viewmodel;

import edu.westga.comp4420.project1.model.Student;
import edu.westga.comp4420.project1.model.StudyGroupManager;

/** Holds the state shared by every window: the study groups and the signed in student.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class AppSession {

	private static final AppSession SHARED = new AppSession(new StudyGroupManager());

	private final StudyGroupManager manager;
	private Student currentStudent;

	/** Creates a session around the given manager. No student is signed in yet.
	 *
	 * @precondition manager != null
	 * @postcondition getCurrentStudent() == null && getManager() == manager
	 *
	 * @param manager the manager that owns the study groups
	 */
	public AppSession(StudyGroupManager manager) {
		if (manager == null) {
			throw new IllegalArgumentException("manager must not be null");
		}
		this.manager = manager;
	}

	/** Gets the session used by the running application.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the shared session
	 */
	public static AppSession getShared() {
		return SHARED;
	}

	/** Gets the manager that owns the study groups.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the manager
	 */
	public StudyGroupManager getManager() {
		return this.manager;
	}

	/** Gets the signed in student.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the student, or null if nobody has signed in
	 */
	public Student getCurrentStudent() {
		return this.currentStudent;
	}

	/** Signs a student in.
	 *
	 * @precondition student != null
	 * @postcondition getCurrentStudent() == student
	 *
	 * @param student the student signing in
	 */
	public void signIn(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("student must not be null");
		}
		this.currentStudent = student;
	}
}
