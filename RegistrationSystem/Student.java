package RegistrationSystem;

import java.util.*;
import java.io.*;


/**
 * Allows students to manage their registration
 * or view reports regarding their registration
 * @author Denisa Vataksi
 *
 */
public class Student extends User implements StudentAccess, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<Course> courses = new ArrayList<>();
	Student(){
		super();
	}
	Student(String username,String password,String firstName,String lastName){
		super(username,password,firstName,lastName);
	}
	Student(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		//default username and password
		username = firstName.substring(0, 1) + lastName;
		password = lastName + "2017";
	}
	/**
	 * getter method for course list
	 */
	public ArrayList<Course> getCourses(){
		return courses;
	}
	
	/**
	 * adds a list of courses onto the list of courses
	 * @param courses
	 */
	public void setCourses(ArrayList<Course>courses){
		this.courses.addAll(courses);
	}
	/**
	 * adds an individual course to the array list of course
	 * @param course
	 */
	public void addCourse(Course course){
		this.courses.add(course);
	}
	/**
	 * removes an individual course from the array list of course
	 * @param course
	 */
	public void removeCourse(Course course){
		this.courses.remove(course);
	}
	@Override 
	public void viewAllAvailableCourses(ArrayList<Course> courses){
		int counter = 0; //check to see if none of the courses are available
		for(Course c: courses){
			if(c.getRegisteredStudents() != c.getMaxStudents()){
				System.out.print(c.getName() + ", ");
			}
		}
		if (counter == 0){
			System.out.println("There are not any available courses");
		}
	}
	
	@Override
	public void withdraw(ArrayList<Course> courses, String courseID, int sectionNumber){
		//check to make sure courseID is valid
				String check = "nothing";
				Course course = null;
				for (Course c: courses){
					if(c.getID().equals(courseID) && c.getSectionNumber() == sectionNumber){
						check = " "; //no longer null
						course = c;
					}
				}
				if (check != "nothing"){
					//check to make sure student was not already registered
					if(courses.contains(course)){
						course.removeStudent(this);//remove from list in course class
						this.courses.remove(course); //remove from list in student class
					}else{
						System.out.println("You were already registered for this course");
					}
				}else{
					System.out.println("That course id is invalid");
					return;
				}
	}
	
	
	public void register(ArrayList<Course> courses,String courseID, int sectionNumber){
		//check to make sure courseID is valid
		String check = "nothing";
		Course course = new Course();
		//match course being registered for with courses available
		for (Course c: courses){
			if(c.getID().equals(courseID) && c.getSectionNumber() == sectionNumber){
				check = " "; //no longer nothing
				course = c;
			}
		}	
		if (!check.equals("nothing")){
			if(course.getRegisteredStudents() < course.getMaxStudents()){ //make sure class has room for another student
				//check to make sure student was not already registered
				if(!this.courses.contains(course)){
					this.courses.add(course); //add to list in student class
					if(!(course.getStudents().contains(this))){
						course.addStudent(this);
					}
						
				}else{
					System.out.println("You are already registered for this course");
				}
			}else{
				System.out.println("Sorry this class is full."); 
			}
		}else{
			System.out.println("That course id is invalid");
			return;
		}
	}
	
	@Override 
	public void viewCourses(){
		System.out.println("You are currently registered for: ");
		for (Course c: courses){
			System.out.print(c.getName() + ", ");
		}
	}
}
