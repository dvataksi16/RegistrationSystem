package RegistrationSystem;
import java.util.*;
import java.io.*;


/**
 * Basic User Characteristics and Behaviors 
 * @author Denisa Vataksi
 *
 */
public abstract class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	
	protected User(){}
	
	protected User(String username,String password,String firstName,String lastName){
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLasttName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	/**
	 * view all courses
	 * @param courses
	 */
	public void viewAllCourses(ArrayList<Course> courses){
		for (Course c: courses){
			System.out.println("Course Name: " + c.getName() +", Course ID: " + c.getID()+
					", Number of Students Registered: "+ c.getRegisteredStudents()+ 
					", Max Students Allowed: " + c.getMaxStudents());
		}
	}
	
	

}
