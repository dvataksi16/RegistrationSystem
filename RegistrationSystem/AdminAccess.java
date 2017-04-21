package RegistrationSystem;
import java.util.*;

/**
 * inteface for Admin
 * @author Denisa Vataksi
 *
 */
public interface AdminAccess {
	//courses management
	/**
	 * creates a new course object
	 * @param name, id, maxStudents, registeredStudents, students, instructor, sectionNumber, location
	 * @return Course
	 */
	public abstract Course createNewCourse(String name, String id, int maxStudents, int registeredStudents, ArrayList<Student> students,
			String instructor, int sectionNumber, String location);
	/**
	 * deletes a course object
	 * @param courses, courseID, sectionNumber
	 */
	public abstract ArrayList<Course> deleteCourse(ArrayList<Course> courses,String courseId, int sectionNumber);
	/**
	 * edits course object information
	 * @param courses, courseID, sectionNumber
	 */
	public abstract void editCourse(ArrayList<Course> courses, String courseID, int sectionNumber);
	/**
	 * displays the info for a given course
	 * @param courses, courseID, sectionNumber
	 */
	public abstract void displayInfo(ArrayList<Course> courses, String courseID, int sectionNumber);
	
	//reports
	/**
	 * view all full courses
	 * @param courses
	 * @return ArrayList<Course>
	 */
	public abstract ArrayList<Course> viewAllFullCourses(ArrayList<Course> courses);
	/**
	 * write to a file the list of course that are full
	 * @param courses
	 * @return ArrayList<Course>
	 */
	public abstract ArrayList<Course> listFullCourses(ArrayList<Course> courses);
	/**
	 * view the names of the students for a specific course
	 * @param courses, courseId, sectionNumber
	 */
	public abstract void viewStudents(ArrayList<Course> courses,String courseId, int sectionNumber);
	/**
	 * sort courses based on the current number of student registers
	 * @param courses
	 */
	public abstract void sortCourses(ArrayList<Course> courses);
	/**
	 * view list of courses student is registered for
	 * @param students, firstName, lastName
	 */
	public abstract void viewCourses(ArrayList<Student> students,String firstName, String lastName);
	
}
