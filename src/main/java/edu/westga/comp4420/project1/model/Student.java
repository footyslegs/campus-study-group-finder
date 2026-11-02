package edu.westga.comp4420.project1.model;

/** A student who can organize and join study groups.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class Student {

	private final String name;
	private final String studentId;

	/** Creates a student with the given name and identifier.
	 *
	 * @precondition name != null && !name.isBlank() && studentId != null && !studentId.isBlank()
	 * @postcondition getName().equals(name.trim()) && getStudentId().equals(studentId.trim())
	 *
	 * @param name the student's display name
	 * @param studentId the student's unique identifier
	 */
	public Student(String name, String studentId) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("name must not be blank");
		}
		if (studentId == null || studentId.isBlank()) {
			throw new IllegalArgumentException("student ID must not be blank");
		}
		this.name = name.trim();
		this.studentId = studentId.trim();
	}

	/** Gets the student's name.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/** Gets the student's identifier.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the identifier
	 */
	public String getStudentId() {
		return this.studentId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Student)) {
			return false;
		}
		return this.studentId.equalsIgnoreCase(((Student) other).studentId);
	}

	@Override
	public int hashCode() {
		return this.studentId.toLowerCase().hashCode();
	}

	@Override
	public String toString() {
		return this.name + " (" + this.studentId + ")";
	}
}
