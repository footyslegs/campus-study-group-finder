package edu.westga.comp4420.project1.viewmodel;

import edu.westga.comp4420.project1.model.StudyGroup;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Manages the state of the main window, which lists and searches study groups.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public class MainWindowViewModel {

	private final AppSession session;
	private final StringProperty searchText;
	private final StringProperty welcome;
	private final ObservableList<StudyGroup> groups;
	private final ObjectProperty<StudyGroup> selectedGroup;

	/** Creates the view model and loads every group.
	 *
	 * @precondition session != null && session.getCurrentStudent() != null
	 * @postcondition getGroups() holds every group && searchTextProperty().get().isEmpty()
	 *
	 * @param session the shared application state
	 */
	public MainWindowViewModel(AppSession session) {
		if (session == null) {
			throw new IllegalArgumentException("session must not be null");
		}
		if (session.getCurrentStudent() == null) {
			throw new IllegalArgumentException("a student must be signed in");
		}
		this.session = session;
		this.searchText = new SimpleStringProperty("");
		this.welcome = new SimpleStringProperty("Signed in as " + session.getCurrentStudent());
		this.groups = FXCollections.observableArrayList();
		this.selectedGroup = new SimpleObjectProperty<>();
		this.searchText.addListener((observable, oldValue, newValue) -> this.refresh());
		this.refresh();
	}

	/** Gets the search text property, which filters the group list by course code.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the search text property
	 */
	public StringProperty searchTextProperty() {
		return this.searchText;
	}

	/** Gets the welcome message property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the welcome property
	 */
	public StringProperty welcomeProperty() {
		return this.welcome;
	}

	/** Gets the groups that match the current search.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the observable list of groups
	 */
	public ObservableList<StudyGroup> getGroups() {
		return this.groups;
	}

	/** Gets the property holding the group chosen in the list.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the selected group property, which may hold null
	 */
	public ObjectProperty<StudyGroup> selectedGroupProperty() {
		return this.selectedGroup;
	}

	/** Gets the session this view model works with.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the session
	 */
	public AppSession getSession() {
		return this.session;
	}

	/** Reloads the list from the manager using the current search text.
	 *
	 * @precondition none
	 * @postcondition getGroups() equals the manager's search result for the current text
	 */
	public void refresh() {
		this.groups.setAll(this.session.getManager().search(this.searchText.get()));
	}
}
