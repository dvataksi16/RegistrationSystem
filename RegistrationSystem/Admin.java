package RegistrationSystem;

import java.util.*;

/**
 * Allows Administration of a school to manage or view reports of 
 * the school registeration system //input checks are instituted in the program!
 * @author Denisa Vataksi
 *
 */
import java.io.*;
public class Admin extends User implements AdminAccess,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Admin(){
		super();
	}
	Admin(String username,String password,String firstName,String lastName){
		super(username,password,firstName,lastName);
	}
	@Override
	public Course createNewCourse(String name, String id, int maxStudents, int registeredStudents, ArrayList<Student> students,
			String instructor, int sectionNumber, String location){
		Course newCourse = new Course(name, id, maxStudents, registeredStudents, students,instructor, sectionNumber, location);
		return newCourse;
	}
	@Override
	public ArrayList<Course> deleteCourse(ArrayList<Course> courses,String courseID, int sectionNumber){
		Course course = null;
		for (int i = 0; i < courses.size(); i++){
			//match course id
			if (courses.get(i).getID().equals(courseID) && courses.get(i).getSectionNumber() == sectionNumber){
				course=courses.get(i);
				break;
			}
		}courses.remove(course);return courses;	
	}
	@Override
	public void displayInfo(ArrayList<Course> courses, String courseID, int sectionNumber){
		Course course = new Course();
		int check = 0;
		//search course list
		for (int i = 0; i < courses.size(); i++){
			//match course id
			if (courses.get(i).getID().equals(courseID) && courses.get(i).getSectionNumber() == sectionNumber){
				course=courses.get(i);
				check ++;
				//display the info
				System.out.println("Course name: " + course.getName() + "\nCourse ID: " + courseID + "\nMaximum Number of Students Registered for the Course: " + course.getMaxStudents()
				+ "\nCurrent Number of Registered Students: " + course.getRegisteredStudents() + "\nStudents registered for course " + course.getName() + ": ");
				for(Student s: course.getStudents()){
					System.out.print(s.getFirstName() + " " +s.getLasttName() + ", ");
				}
				System.out.println("Course Instructor: " + course.getInstructor() + "\nCourse Section Number: " + course.getSectionNumber() + "\nCourse Location: " + course.getLocation());
			}//accommodate for the possibility of an invalid course ID entry
		}
		if(check == 0){
			System.out.println("That is not a valid course ID");
		}
	}@Override
	public ArrayList<Course> viewAllFullCourses(ArrayList<Course> courses){
		ArrayList<Course> fullCourses = new ArrayList<>();
		//search through list for full courses
		int count = 0;
		for (Course c: courses){
			if (c.getMaxStudents() == c.getRegisteredStudents()){
				if(count == 0)
					System.out.println("The courses that are full are: ");
				fullCourses.add(c);
				System.out.print(c.getName() + ", ");
			}
		}if (fullCourses.isEmpty()){
			System.out.println("None of the courses are full yet");
		}
		return fullCourses;
	}
	@Override 
	public ArrayList<Course> listFullCourses(ArrayList<Course> courses){
		//new list to be occupied with full courses
		ArrayList<Course> fullCourses = new ArrayList<>();
		//search through all of the courses
		for(Course c: courses){
			//extract full courses from raw course list
			if (c.getMaxStudents() == c.getRegisteredStudents()){
				fullCourses.add(c);
			}
		}return fullCourses;
	}
	@Override
	public void sortCourses(ArrayList<Course> courses){
		for(int c = 0; c < courses.size(); c++){
			Course temp = courses.get(c + 1);
			if(courses.get(c).getRegisteredStudents() < courses.get(c+1).getRegisteredStudents()){
				courses.remove(courses.get(c+1));//remove c +1
				courses.add(c,temp);//place c + 1 in c's position and have c shift over 1
			}
		}
	}
	
	public  void register(Student student, ArrayList<Course> courses, String courseID, int sectionNumber){
		String id = "nothing"; //to check to see if course id is valid
		Course course = new Course();
		for(Course c: courses){ //search through course list
			if (c.getID().equals(courseID) && c.getSectionNumber() == sectionNumber){
				course = c;
				id = courseID; //course id is valid!
				break;
			}
		}if(course.getRegisteredStudents() < course.getMaxStudents()){ //make sure class has room for another student
			if(!course.getStudents().contains(course))
				course.addStudent(student); //register the student
			//add to student course list
			if(!student.getCourses().contains(course))
				student.addCourse(course);
		}
		else{
			System.out.println("Sorry this class is full."); return;
		}
		if (id == "nothing"){
			System.out.println("This course does not exist");
		}
	}
	@Override
	public void viewCourses(ArrayList<Student> students, String firstName, String lastName){
		Student student = new Student();
		for (int i = 0; i < students.size(); i++){
			if ((students.get(i).getFirstName().equalsIgnoreCase(firstName)) && (students.get(i).getLasttName().equalsIgnoreCase(lastName))){
				student = students.get(i);
				break;
			}
		}if(!student.getCourses().isEmpty()){
			System.out.println(student.getFirstName() + " " + student.getLasttName() + " is registered for: ");
			for(int k = 0; k < student.getCourses().size(); k++){
				System.out.print(student.getCourses().get(k).getName() + ", ");
			}
		}else{
			System.out.println(student.getFirstName() + " " + student.getLasttName() + " is not registered for any classes");
		}	
		
	}
	
	@Override
	public void editCourse(ArrayList<Course> courses, String courseID, int sectionNumber){
		Course course = null;
		for (int i = 0; i < courses.size(); i++){
			//match course id
			if (courses.get(i).getID().equals(courseID) && courses.get(i).getSectionNumber() == sectionNumber){
				course=courses.get(i);
				break;
			}
		}
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like to edit (name/id/maxStudents/instructor/sectionNumber/location)? ");
		String edit = input.nextLine();
		switch (edit){
		case "name":System.out.println("What would you like to change the name of the course to?");
			String newName = input.nextLine(); course.setName(newName); break;
		case "id": System.out.println("What would you like to change the Course id number to? ");
			String newID = input.nextLine(); course.setID(newID); break;
		case "maxStudents":System.out.println("What would you like to change the maximum number of students allowed for this course to? ");
			int newMax = input.nextInt(); course.setMaxStudents(newMax); break;
		case "instructor":System.out.println("Who would you like to change the instructor to? ");
			String newInstructor = input.nextLine(); course.setInstructor(newInstructor);break;
		case "sectionNumber": System.out.println("What would you like to change the section number to ?");
			int newSectionNum = input.nextInt(); course.setSectionNumber(newSectionNum);break;
		case "location": System.out.println("Where would you like to change the location to? ");
			String newLocation = input.nextLine(); course.setLocation(newLocation);break;
		default: System.out.println("That input is invalid");
		}
	}
	
	@Override 
	public void viewStudents(ArrayList<Course>courses, String courseID, int sectionNumber){
		Course course = new Course();
		for (int i = 0; i < courses.size(); i++){
			//match course id
			if ((courses.get(i).getID().equals(courseID)) && (courses.get(i).getSectionNumber() == sectionNumber)){
				course=courses.get(i);
				break;
			}
		}
		System.out.println("The students currently registered for this course are:");
		for (Student s: course.getStudents()){
			System.out.print(s.getFirstName() + " " + s.getLasttName() + ", ");
		}
	}
	
		
}
