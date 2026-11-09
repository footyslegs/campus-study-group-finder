package edu.westga.comp4420.project1.viewmodel;

import edu.westga.comp4420.project1.model.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** Manages the sign in window, where a student enters a name and ID.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class SignInViewModel {

	private final AppSession session;
	private final StringProperty name;
	private final StringProperty studentId;
	private final StringProperty message;

	/** Creates the view model with empty inputs.
	 *
	 * @precondition session != null
	 * @postcondition all properties hold empty text
	 *
	 * @param session the shared application state
	 */
	public SignInViewModel(AppSession session) {
		if (session == null) {
			throw new IllegalArgumentException("session must not be null");
		}
		this.session = session;
		this.name = new SimpleStringProperty("");
		this.studentId = new SimpleStringProperty("");
		this.message = new SimpleStringProperty("");
	}

	/** Gets the name input property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the name property
	 */
	public StringProperty nameProperty() {
		return this.name;
	}

	/** Gets the student ID input property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the student ID property
	 */
	public StringProperty studentIdProperty() {
		return this.studentId;
	}

	/** Gets the message property, which describes why sign in failed.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the message property
	 */
	public StringProperty messageProperty() {
		return this.message;
	}

	/** Signs in the student described by the inputs.
	 *
	 * @precondition none
	 * @postcondition if true is returned, the session's current student has the entered name and ID;
	 * 					otherwise messageProperty() describes the problem
	 *
	 * @return true if sign in succeeded
	 */
	public boolean signIn() {
		try {
			this.session.signIn(new Student(this.name.get(), this.studentId.get()));
			this.message.set("");
			return true;
		} catch (IllegalArgumentException e) {
			this.message.set("Enter both your name and your student ID.");
			return false;
		}
	}
}
