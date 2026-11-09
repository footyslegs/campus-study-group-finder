package edu.westga.comp4420.project1.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A study group for a course, with a capacity limit and a list of members.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class StudyGroup {

	/** The largest capacity a group may have. */
	public static final int MAXIMUM_CAPACITY = 50;

	/** The smallest capacity a group may have. */
	public static final int MINIMUM_CAPACITY = 2;

	/** The text format used to show meeting times, for example 2026-12-01 18:30. */
	public static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	private static final String SUMMARY_FORMAT = "%s - %s | %s | %s | %d/%d members";
	private static final String STUDENT_NULL = "student must not be null";

	private final String courseCode;
	private final String title;
	private final String location;
	private final LocalDateTime meetingTime;
	private final int capacity;
	private final Student organizer;
	private final List<Student> members;

	/** Creates a study group. The organizer is automatically its first member.
	 *
	 * @precondition courseCode is a valid course code && title != null && !title.isBlank()
	 * 					&& location != null && !location.isBlank() && meetingTime != null
	 * 					&& MINIMUM_CAPACITY <= capacity <= MAXIMUM_CAPACITY && organizer != null
	 * @postcondition getMembers().size() == 1 && isMember(organizer)
	 *
	 * @param courseCode the course the group studies, such as COMP 4420
	 * @param title a short title for the group
	 * @param location where the group meets
	 * @param meetingTime when the group meets
	 * @param capacity the maximum number of members
	 * @param organizer the student who created the group
	 */
	public StudyGroup(String courseCode, String title, String location, LocalDateTime meetingTime, int capacity,
			Student organizer) {
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("title must not be blank");
		}
		if (location == null || location.isBlank()) {
			throw new IllegalArgumentException("location must not be blank");
		}
		if (meetingTime == null) {
			throw new IllegalArgumentException("meeting time must not be null");
		}
		if (capacity < MINIMUM_CAPACITY || capacity > MAXIMUM_CAPACITY) {
			throw new IllegalArgumentException(
					"capacity must be between " + MINIMUM_CAPACITY + " and " + MAXIMUM_CAPACITY);
		}
		if (organizer == null) {
			throw new IllegalArgumentException("organizer must not be null");
		}
		this.courseCode = CourseCode.normalize(courseCode);
		this.title = title.trim();
		this.location = location.trim();
		this.meetingTime = meetingTime;
		this.capacity = capacity;
		this.organizer = organizer;
		this.members = new ArrayList<>();
		this.members.add(organizer);
	}

	/** Gets the normalized course code.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the course code
	 */
	public String getCourseCode() {
		return this.courseCode;
	}

	/** Gets the title.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/** Gets the meeting location.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/** Gets the meeting time.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the meeting time
	 */
	public LocalDateTime getMeetingTime() {
		return this.meetingTime;
	}

	/** Gets the maximum number of members.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/** Gets the student who created the group.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the organizer
	 */
	public Student getOrganizer() {
		return this.organizer;
	}

	/** Gets a read-only view of the members.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the members in the order they joined
	 */
	public List<Student> getMembers() {
		return Collections.unmodifiableList(this.members);
	}

	/** Gets the number of members.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the member count
	 */
	public int getMemberCount() {
		return this.members.size();
	}

	/** Checks whether the group has reached its capacity.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return true if no more students can join
	 */
	public boolean isFull() {
		return this.members.size() >= this.capacity;
	}

	/** Checks whether the student belongs to the group.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param student the student to look for
	 *
	 * @return true if the student is a member
	 */
	public boolean isMember(Student student) {
		return this.members.contains(student);
	}

	/** Adds a student to the group.
	 *
	 * @precondition student != null && !isMember(student) && !isFull()
	 * @postcondition isMember(student)
	 *
	 * @param student the student joining
	 */
	public void addMember(Student student) {
		if (student == null) {
			throw new IllegalArgumentException(STUDENT_NULL);
		}
		if (this.isMember(student)) {
			throw new IllegalStateException("student is already a member of this group");
		}
		if (this.isFull()) {
			throw new IllegalStateException("group is full");
		}
		this.members.add(student);
	}

	/** Removes a student from the group. The organizer cannot leave their own group.
	 *
	 * @precondition student != null && isMember(student) && !student.equals(getOrganizer())
	 * @postcondition !isMember(student)
	 *
	 * @param student the student leaving
	 */
	public void removeMember(Student student) {
		if (student == null) {
			throw new IllegalArgumentException(STUDENT_NULL);
		}
		if (student.equals(this.organizer)) {
			throw new IllegalStateException("the organizer cannot leave their own group");
		}
		if (!this.isMember(student)) {
			throw new IllegalStateException("student is not a member of this group");
		}
		this.members.remove(student);
	}

	@Override
	public String toString() {
		return String.format(SUMMARY_FORMAT, this.courseCode, this.title, this.meetingTime.format(DISPLAY_FORMAT),
				this.location, this.members.size(), this.capacity);
	}
}
