package RegistrationSystem;

import java.util.*;
import java.io.*;

/**
 * Establishes a course in a school registration system
 * @author Denisa Vataksi
 *
 */

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String id;
	protected int maxStudents;
	protected int registeredStudents;
	protected ArrayList<Student> students = new ArrayList<>();
	protected String instructor;
	protected int sectionNumber;
	protected String location;
	
	Course(){}
	Course(String name, String id, int maxStudents, int registeredStudents, ArrayList<Student> students,
			String instructor, int sectionNumber, String location){
		this.name = name;
		this.id = id;
		this.maxStudents = maxStudents;
		this.registeredStudents = registeredStudents;
		this.students = students;
		this.instructor = instructor;
		this.sectionNumber = sectionNumber;
		this.location = location;
	}
	
	//setters and getters
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	
	public void setID(String id){
		this.id = id;
	}
	public String getID(){
		return id;
	}
	
	public void setMaxStudents(int maxStudents){
		this.maxStudents=maxStudents;
	}
	public int getMaxStudents(){
		return maxStudents;
	}
	
	public void setRegisteredStudents(int registeredStudents){
		this.registeredStudents=registeredStudents;
	}
	public int getRegisteredStudents(){
		return registeredStudents;
	}
	/**
	 * adds a list of students onto the list of students
	 * @param students
	 */
	public void addStudents(ArrayList<Student>students){
		this.students.addAll(students);
		registeredStudents += students.size();
	}
	/**
	 * adds an individual student to the array list of students
	 * @param student
	 */
	public void addStudent(Student student){
		this.students.add(student);
		registeredStudents ++;
	}
	/**
	 * removes an individual student from the array list of students
	 * @param student
	 */
	public void removeStudent(Student student){
		this.students.remove(student);
		registeredStudents --;
	}
	public ArrayList<Student> getStudents(){
		return students;
	}
	
	public void setInstructor(String instructor){
		this.instructor=instructor;
	}
	public String getInstructor(){
		return instructor;
	}
	
	public void setSectionNumber(int sectionNumber){
		this.sectionNumber=sectionNumber;
	}
	public int getSectionNumber(){
		return sectionNumber;
	}
	
	public void setLocation(String location){
		this.location=location;
	}
	public String getLocation(){
		return location;
	}
	
}
