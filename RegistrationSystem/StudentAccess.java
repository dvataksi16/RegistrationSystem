package RegistrationSystem;
import java.util.*;

/**
 * interface for Student
 * @author Denisa Vataksi
 *
 */
public interface StudentAccess {

	/**
	 * allows student to view all courses that are not full
	 * @param courses
	 */
	public abstract void viewAllAvailableCourses(ArrayList<Course> courses);
	/**
	 * withdraw from a course:
	 * must enter student name and course and name will be taken off of course roster
	 * @param student, courses, courseID, sectionNumber
	 */
	public abstract void withdraw(ArrayList<Course> courses, String courseID, int sectionNumber);
	/**
	 * view list of courses student is registered for
	 */
	public abstract void viewCourses();
}	