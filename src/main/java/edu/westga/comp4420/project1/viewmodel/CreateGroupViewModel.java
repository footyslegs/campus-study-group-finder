package edu.westga.comp4420.project1.viewmodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import edu.westga.comp4420.project1.model.StudyGroup;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** Manages the Create Group window: validates the entered text and asks the manager to create the group.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class CreateGroupViewModel {

	private final AppSession session;
	private final StringProperty courseCode;
	private final StringProperty title;
	private final StringProperty location;
	private final StringProperty meetingTime;
	private final StringProperty capacity;
	private final StringProperty message;

	/** Creates the view model with empty inputs.
	 *
	 * @precondition session != null && session.getCurrentStudent() != null
	 * @postcondition all properties hold empty text
	 *
	 * @param session the shared application state
	 */
	public CreateGroupViewModel(AppSession session) {
		if (session == null) {
			throw new IllegalArgumentException("session must not be null");
		}
		if (session.getCurrentStudent() == null) {
			throw new IllegalArgumentException("a student must be signed in");
		}
		this.session = session;
		this.courseCode = new SimpleStringProperty("");
		this.title = new SimpleStringProperty("");
		this.location = new SimpleStringProperty("");
		this.meetingTime = new SimpleStringProperty("");
		this.capacity = new SimpleStringProperty("");
		this.message = new SimpleStringProperty("");
	}

	/** Gets the course code input property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the course code property
	 */
	public StringProperty courseCodeProperty() {
		return this.courseCode;
	}

	/** Gets the title input property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the title property
	 */
	public StringProperty titleProperty() {
		return this.title;
	}

	/** Gets the location input property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the location property
	 */
	public StringProperty locationProperty() {
		return this.location;
	}

	/** Gets the meeting time input property, written as yyyy-MM-dd HH:mm.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the meeting time property
	 */
	public StringProperty meetingTimeProperty() {
		return this.meetingTime;
	}

	/** Gets the capacity input property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the capacity property
	 */
	public StringProperty capacityProperty() {
		return this.capacity;
	}

	/** Gets the message property, which describes why creation failed.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the message property
	 */
	public StringProperty messageProperty() {
		return this.message;
	}

	/** Creates a group from the inputs with the signed in student as organizer.
	 *
	 * @precondition none
	 * @postcondition if true is returned, the manager holds a new group built from the inputs;
	 * 					otherwise nothing was added and messageProperty() describes the problem
	 *
	 * @return true if the group was created
	 */
	public boolean createGroup() {
		try {
			int parsedCapacity = Integer.parseInt(this.capacity.get().trim());
			LocalDateTime parsedTime = LocalDateTime.parse(this.meetingTime.get().trim(), StudyGroup.DISPLAY_FORMAT);
			this.session.getManager().createGroup(this.courseCode.get(), this.title.get(), this.location.get(),
					parsedTime, parsedCapacity, this.session.getCurrentStudent());
			this.message.set("");
			return true;
		} catch (NumberFormatException e) {
			this.message.set("Capacity must be a whole number.");
		} catch (DateTimeParseException e) {
			this.message.set("Meeting time must look like 2026-12-01 18:30.");
		} catch (IllegalArgumentException e) {
			this.message.set(e.getMessage());
		}
		return false;
	}
}
