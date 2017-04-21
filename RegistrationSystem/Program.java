package RegistrationSystem;

import java.io.*; import java.util.*;

/***
 * School Registration Program
 * @author Denisa Vataksi
 *
 */
public class Program {

	public static void main(String[] args) throws IOException {
		File file = new File ("MyUniversityCourses.csv");//first time
		File serializedStudentFile = new File("Students.ser");//once serialized 
		File serializedCourseFile = new File ("Courses.ser");
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Student> students = new ArrayList<Student>();
		//assuming there is only one admin
		Admin admin = new Admin("Admin", "Admin001","Ad","Min");
		
		if(!serializedCourseFile.exists()){//csv file is used to initialize the course arraylist
			Scanner input = new Scanner (file);
			//iterate through file
			
			while(input.hasNext()){
				String courseRawData = input.nextLine();
				//skip over header
				if (courseRawData.contains("Course_Name"))
					continue;
				else{
					String[]courseInfo = courseRawData.split(",");
					String name = courseInfo[0];
					String id = courseInfo[1];
					int maxStudents = Integer.parseInt(courseInfo[2]);
					int registeredStudents = Integer.parseInt(courseInfo[3]); 
					String instructor = courseInfo[5];
					int sectionNumber = Integer.parseInt(courseInfo[6]); 
					String location = courseInfo[7];
					
					Course course = new Course(name, id, maxStudents,registeredStudents, students, instructor, sectionNumber, location);
					courses.add(course);
				}
			}
		}
		
		else{
			try{
				//populate course array list
				FileInputStream fisCourses = new FileInputStream("Courses.ser");
				ObjectInputStream oisCourses = new ObjectInputStream(fisCourses);
				courses = (ArrayList) oisCourses.readObject();
				oisCourses.close(); fisCourses.close();
				
				//populate student array list
				FileInputStream fisStudents = new FileInputStream("Students.ser");
				ObjectInputStream oisStudents = new ObjectInputStream(fisStudents);
				students = (ArrayList) oisStudents.readObject();
				oisStudents.close(); fisStudents.close();
				
				
			}catch(IOException ioe){
				ioe.printStackTrace();
				return;
			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
				return;
			}
		}
		
		Scanner input2 = new Scanner(System.in);
		String login = "y";
		while(login.equalsIgnoreCase("y")){
			System.out.println("Student or Admin?");
			String user = input2.next();
			//ADMIN
			if(user.equalsIgnoreCase("admin")){
				System.out.println("Enter your username: ");
				String username = input2.next();
				while(!username.equals(admin.getUsername())){ //only continue once username is validated
					System.out.println("Sorry that username does not exist");
					System.out.println("Enter your username: ");
					username = input2.next();	
				}
				System.out.println("Enter your password:");
				String password = input2.next();
				//check the validity of the password using while loop & allow user to retry 
				while (!password.equals(admin.getPassword())){
					System.out.println("Incorrect password. Try again ");
					password = input2.nextLine();
				}
				
				System.out.println("Welcome to the Registeration System!");
				String option = "h";
				int manageOrReport = 0;
				while ((!(option.equals("6") || option.equals("7"))) && (manageOrReport != 3)){
					System.out.println("Press 1 to manage a course 2 to view a course report or 3 to exit");
					manageOrReport = input2.nextInt();
					if(manageOrReport == 1){
						System.out.println("Press 1 to create a new course, 2 to delete a course, 3 to edit a course, 4 to display information for a given course, "
								+ " 5 to register a student or 6 to exit");
						option = input2.next(); //to eliminate an error just in case a user inputs a non numeric value
						switch(option){
						case "1": 
							System.out.println("Course Name? "); String name = input2.next();
							System.out.println("Course ID? "); String id = input2.next();
							System.out.println("What is the maximum of students allowed in this course? ");int maxStudents = input2.nextInt();
							System.out.println("Enter the first name followed by the last name of each student registered already,separating each of them with a comma." +
							"If no one is registered yet type in 0");
							String studentRawList = input2.next();
							ArrayList<Student> studentsList = new ArrayList<>();
							if(studentRawList.equals("0")){}
							else{
								//split students by comma and place into temp String array
								String[] studentData = studentRawList.split(",");
								for(int i = 0; i < studentData.length; i++){
									//further split the array
									String[] firstAndLastName = studentData[0].split(" ");
									String first = firstAndLastName[0];
									String last = firstAndLastName[1];
									Student student = new Student(first, last); //convert into student object
									studentsList.add(student); //add student object to final student list
								}
							}
							System.out.println("Who is the instructor? "); String instructor = input2.nextLine();
							System.out.println("What is the section number?"); int sectionNumber = input2.nextInt();
							System.out.println("Where is the class located? "); String location = input2.nextLine();
							admin.createNewCourse(name, id, maxStudents, studentsList.size(), studentsList, instructor, sectionNumber, location); break;
						case "2": 
							System.out.println("Course ID? ");id = input2.next();
							System.out.println("What is the section number? "); sectionNumber = input2.nextInt();
							admin.deleteCourse(courses, id, sectionNumber);break;
						case "3": 
							System.out.println("What is the course ID? "); id = input2.next();
							System.out.println("What is the section number?"); sectionNumber = input2.nextInt();
							admin.editCourse(courses, id, sectionNumber); break;
						case "4":
							System.out.println("What is the course ID? "); id = input2.next();
							System.out.println("What is the section number?"); sectionNumber = input2.nextInt();
							admin.displayInfo(courses, id, sectionNumber);break;
						case "5": 
							System.out.println("Would you like to register a student to a course? (y/n)"); //option to register student to the system or to the system and course
							String register = input2.next();
							if(register.equalsIgnoreCase("n")){
								System.out.println("What is the student's first name?");String first = input2.next();
								System.out.println("What is the student's last name?");String last = input2.next();
								Student student = new Student(first, last);
								if (!students.contains(student)){
									students.add(student);
								}
								
							}else if(register.equalsIgnoreCase("y")){
								System.out.println("What is the student's first name?");String first = input2.next();
								System.out.println("What is the student's last name?");String last = input2.next();
								Student student = new Student(first, last);
								if (!students.contains(student)){
									students.add(student);
								}System.out.println("What is the course ID? "); 
								id = input2.next();
								System.out.println("What is the section number?"); 
								sectionNumber = input2.nextInt();
								admin.register(student, courses, id, sectionNumber);
							}
						case "6":
							break;
						default: 
							System.out.println("That is not a valid entry"); break;	
						}
					}
					if(manageOrReport == 2){
						System.out.println("Press 1 to view all courses, 2 to view all courses that are full, 3 to write to a file a list of course that are full, "
								+ "4 to view the names of the students being registered in a specific course, 5 to view the list of courses that a given student is being registered for, "
								+ "6 to sort the courses or 7 to exit");
						option = input2.next();
						switch(option){
						case "1":
							admin.viewAllCourses(courses);break;
						case "2": 
							admin.viewAllFullCourses(courses); break;
						case "3": 
							PrintWriter output = new PrintWriter("MyUniversityFullCourses.csv");
							ArrayList<Course> fullCourses = admin.viewAllFullCourses(courses);
							for(Course c: fullCourses){
								output.println(""+c.getName() +"," + c.getID()+","+c.getMaxStudents()+","+c.getRegisteredStudents() + "," 
							+ c.getStudents().toString()+ "," +c.getSectionNumber() +c.getLocation());
							}
							output.close();break;
						case "4": 
							System.out.println("What is the course ID? "); String id = input2.next();
							System.out.println("What is the section number?"); int sectionNumber = input2.nextInt();
							admin.viewStudents(courses, id, sectionNumber);break;
						case "5":
							System.out.println("What is the student's first name?");String first = input2.next();
							System.out.println("What is the student's last name?");String last = input2.next();
							admin.viewCourses(students, first, last);break;
						case "6": 
							admin.sortCourses(courses); System.out.println("Courses sorted.");break;
						case "7": 
							break;
						default: 
							System.out.println("That is not a valid entry"); break;	
						}
					}
				}
			}//STUDENT
			if(user.equalsIgnoreCase("student")){
				Student student = new Student(); 
				System.out.println("Enter your username: ");
				String username = input2.next();
				int check = 0;
				while (check == 0){
					for (int i = 0; i < students.size(); i ++){
						if(username.equalsIgnoreCase(students.get(i).getUsername())){ //only continue once username is validated
							student = students.get(i);
							check = 1;
							break;
						}
						
				}	if (check == 0){
						System.out.println("Sorry that username is not valid. Try again.");
						username = input2.next();
					}
				}
				System.out.println("Enter your password: ");
				String password = input2.next();
				while (!password.equalsIgnoreCase(student.getPassword())){ //only continue once password is validated
						System.out.println("Incorrect password. Try again.");
						password = input2.next();
				}
				System.out.println("Welcome to the Registeration System!");
				String option = "nothing";
				while(!option.equals("6")){
					System.out.println("Press 1 to view all courses, 2 to view all courses that are not full,"
							+ " 3 to register on a course, 4 to withdraw from a course, 5 to view all courses that you are being registered in or 6 to exit.");
					option = input2.next();
					switch(option){
					case "1": 
						student.viewAllCourses(courses); break;
					case "2": 
						student.viewAllAvailableCourses(courses); break;
					case "3": 
						System.out.println("Course ID? "); String id = input2.next();
						System.out.println("Section number? "); int sectionNumber = input2.nextInt();
						student.register(courses, id, sectionNumber); break;
					case "4": 
						System.out.println("Course ID? "); 
						String courseID = input2.next();
						System.out.println("Section number? "); 
						int sectionNum = input2.nextInt();
						student.withdraw(courses, courseID, sectionNum); break;
					case "5": 
						student.viewCourses(); break;
					case "6": 
						break;
					default: 
						System.out.println("That is not a valid entry"); break;
					}
				}	
			}
			Scanner input3 = new Scanner(System.in);
			System.out.println("Would you like to login again?(y/n)");
			login = input3.next();
		}	
		
		
		if(login.equalsIgnoreCase("n")){ 
			//serialization
			try{
				//students array list
				FileOutputStream fosStudents = new FileOutputStream("Students.ser");
				ObjectOutputStream oosStudents = new ObjectOutputStream(fosStudents);
				
				oosStudents.writeObject(students);
	
				oosStudents.close();
				fosStudents.close();
				
				//courses array list
				FileOutputStream fosCourses = new FileOutputStream("Courses.ser");
				ObjectOutputStream oosCourses = new ObjectOutputStream(fosCourses);
				
			
				oosCourses.writeObject(courses);
				
				oosCourses.close();
				fosCourses.close();
				
				System.out.println("Serialization completed");
				
				
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		}	
	}
}
