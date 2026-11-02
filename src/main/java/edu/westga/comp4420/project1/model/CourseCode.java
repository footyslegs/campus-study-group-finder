package edu.westga.comp4420.project1.model;

import java.util.regex.Pattern;

/** Validates and normalizes university course codes such as COMP 4420.
 *
 * @author Ayoola Morakinyo
 * @version Fall 2026
 */
public final class CourseCode {

	private static final Pattern VALID_CODE = Pattern.compile("^([A-Za-z]{2,4})\\s*(\\d{4})$");

	private CourseCode() {
		// utility class, not meant to be instantiated
	}

	/** Checks whether the text is a valid course code.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param text the text to check
	 *
	 * @return true if the text is two to four letters followed by four digits
	 */
	public static boolean isValid(String text) {
		return text != null && VALID_CODE.matcher(text.trim()).matches();
	}

	/** Converts a valid course code to its canonical form, for example "comp4420" to "COMP 4420".
	 *
	 * @precondition isValid(text)
	 * @postcondition none
	 *
	 * @param text the course code to normalize
	 *
	 * @return the canonical course code
	 */
	public static String normalize(String text) {
		if (!CourseCode.isValid(text)) {
			throw new IllegalArgumentException("course code must look like COMP 4420");
		}
		var matcher = VALID_CODE.matcher(text.trim());
		matcher.matches();
		return matcher.group(1).toUpperCase() + " " + matcher.group(2);
	}
}
