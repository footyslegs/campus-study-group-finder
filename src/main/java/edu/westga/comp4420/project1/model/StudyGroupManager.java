package edu.westga.comp4420.project1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Keeps track of every study group and offers creating and searching them.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class StudyGroupManager {

	private final List<StudyGroup> groups;

	/** Creates a manager that has no groups yet.
	 *
	 * @precondition none
	 * @postcondition getGroups().isEmpty()
	 */
	public StudyGroupManager() {
		this.groups = new ArrayList<>();
	}

	/** Gets a read-only view of all groups, in the order they were created.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return all groups
	 */
	public List<StudyGroup> getGroups() {
		return Collections.unmodifiableList(this.groups);
	}

	/** Creates a group and adds it to the manager.
	 *
	 * @precondition the arguments satisfy the StudyGroup constructor preconditions
	 * @postcondition getGroups().contains(the returned group)
	 *
	 * @param courseCode the course the group studies
	 * @param title a short title for the group
	 * @param location where the group meets
	 * @param meetingTime when the group meets
	 * @param capacity the maximum number of members
	 * @param organizer the student creating the group
	 *
	 * @return the new group
	 */
	public StudyGroup createGroup(String courseCode, String title, String location, LocalDateTime meetingTime,
			int capacity, Student organizer) {
		StudyGroup group = new StudyGroup(courseCode, title, location, meetingTime, capacity, organizer);
		this.groups.add(group);
		return group;
	}

	/** Finds the groups whose course code contains the search text, ignoring case and spaces.
	 * A blank search returns every group.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param searchText the course code, or part of one, to look for
	 *
	 * @return the matching groups, soonest meeting first
	 */
	public List<StudyGroup> search(String searchText) {
		String wanted = StudyGroupManager.simplify(searchText);
		List<StudyGroup> matches = new ArrayList<>();
		for (StudyGroup group : this.groups) {
			if (StudyGroupManager.simplify(group.getCourseCode()).contains(wanted)) {
				matches.add(group);
			}
		}
		matches.sort((first, second) -> first.getMeetingTime().compareTo(second.getMeetingTime()));
		return matches;
	}

	private static String simplify(String text) {
		if (text == null) {
			return "";
		}
		return text.replace(" ", "").toLowerCase();
	}
}
