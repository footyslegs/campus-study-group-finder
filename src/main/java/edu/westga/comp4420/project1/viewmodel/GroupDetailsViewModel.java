package edu.westga.comp4420.project1.viewmodel;

import edu.westga.comp4420.project1.model.Student;
import edu.westga.comp4420.project1.model.StudyGroup;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Manages the group details window, where the signed in student joins or leaves a group.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class GroupDetailsViewModel {

	private final AppSession session;
	private final StudyGroup group;
	private final StringProperty heading;
	private final StringProperty details;
	private final StringProperty seats;
	private final StringProperty message;
	private final BooleanProperty canJoin;
	private final BooleanProperty canLeave;
	private final ObservableList<String> memberNames;

	/** Creates the view model for one group.
	 *
	 * @precondition session != null && session.getCurrentStudent() != null && group != null
	 * @postcondition the properties describe the group
	 *
	 * @param session the shared application state
	 * @param group the group to show
	 */
	public GroupDetailsViewModel(AppSession session, StudyGroup group) {
		if (session == null) {
			throw new IllegalArgumentException("session must not be null");
		}
		if (session.getCurrentStudent() == null) {
			throw new IllegalArgumentException("a student must be signed in");
		}
		if (group == null) {
			throw new IllegalArgumentException("group must not be null");
		}
		this.session = session;
		this.group = group;
		this.heading = new SimpleStringProperty(group.getCourseCode() + ": " + group.getTitle());
		this.details = new SimpleStringProperty(
				group.getLocation() + ", " + group.getMeetingTime().format(StudyGroup.DISPLAY_FORMAT));
		this.seats = new SimpleStringProperty("");
		this.message = new SimpleStringProperty("");
		this.canJoin = new SimpleBooleanProperty();
		this.canLeave = new SimpleBooleanProperty();
		this.memberNames = FXCollections.observableArrayList();
		this.refresh();
	}

	/** Gets the heading property, which holds the course code and title.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the heading property
	 */
	public StringProperty headingProperty() {
		return this.heading;
	}

	/** Gets the details property, which holds the location and meeting time.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the details property
	 */
	public StringProperty detailsProperty() {
		return this.details;
	}

	/** Gets the seats property, which reads like "2 of 5 seats taken".
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the seats property
	 */
	public StringProperty seatsProperty() {
		return this.seats;
	}

	/** Gets the message property, which describes the last problem or result.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the message property
	 */
	public StringProperty messageProperty() {
		return this.message;
	}

	/** Gets the property that is true when the signed in student may join.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the can join property
	 */
	public BooleanProperty canJoinProperty() {
		return this.canJoin;
	}

	/** Gets the property that is true when the signed in student may leave.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the can leave property
	 */
	public BooleanProperty canLeaveProperty() {
		return this.canLeave;
	}

	/** Gets the display names of the members.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the observable list of member names
	 */
	public ObservableList<String> getMemberNames() {
		return this.memberNames;
	}

	/** Adds the signed in student to the group if there is room.
	 *
	 * @precondition none
	 * @postcondition if the student could join they are a member, otherwise messageProperty() says why
	 */
	public void join() {
		try {
			this.group.addMember(this.session.getCurrentStudent());
			this.message.set("You joined the group.");
		} catch (IllegalStateException e) {
			this.message.set(e.getMessage());
		}
		this.refresh();
	}

	/** Removes the signed in student from the group.
	 *
	 * @precondition none
	 * @postcondition if the student could leave they are no longer a member, otherwise messageProperty() says why
	 */
	public void leave() {
		try {
			this.group.removeMember(this.session.getCurrentStudent());
			this.message.set("You left the group.");
		} catch (IllegalStateException e) {
			this.message.set(e.getMessage());
		}
		this.refresh();
	}

	private void refresh() {
		Student student = this.session.getCurrentStudent();
		this.seats.set(this.group.getMemberCount() + " of " + this.group.getCapacity() + " seats taken");
		this.canJoin.set(!this.group.isMember(student) && !this.group.isFull());
		this.canLeave.set(this.group.isMember(student) && !student.equals(this.group.getOrganizer()));
		this.memberNames.clear();
		for (Student member : this.group.getMembers()) {
			this.memberNames.add(member.toString());
		}
	}
}
