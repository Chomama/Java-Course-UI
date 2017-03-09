/**Ryan CHo
 * Admin Class 
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Admin extends User implements AdminInterface, java.io.Serializable{
	static ArrayList <Course> courseList=new ArrayList<>();  
	
	public static void main(String[]args) {
	
	}

	//Constructor for Admin Object
	public Admin(String username, String password, String firstName, String lastName, ArrayList <Course> courseList) {
		super(username, password, firstName, lastName);
		this.courseList=courseList;
		// TODO Auto-generated constructor stub
	}

	 
	@Override //Method that creates a new course and adds to courseList
	public void createNewCourse(String courseName, String courseID, int maxStudents, int currentStudents, ArrayList <String> students, String instructor, int courseSectionNumber,  String courseLocation) {
		Course course = new Course(courseName, courseID, maxStudents, currentStudents, students, instructor,
				courseSectionNumber, courseLocation);
		courseList.add(course);  //Adds the newly created course object to the courseList
	}
	
	
	@Override //Method that deletes a course from the courseList
	public void deleteCourse(String courseName) {

		for(int x=0; x<courseList.size();x++) {
			if (courseList.get(x).getCourseName().equals(courseName)) {
				System.out.println(courseList.get(x).getCourseName());
				courseList.remove(x);
			}

		}
		
	}

	
	@Override //Method that allows the Admin to edit information in the classes
	public void editCourse(String courseName) {
		Scanner input = new Scanner(System.in); //scanner for inputs
		System.out.println("What info would you like to edit? (1:Course Name, 2: Course ID, 3: Max Students, 4: Current Students, 5: List of Names, 6: Instructor, 7: Course #, 8: Location): ");
		int info=input.nextInt();  //scanner that reads in what user wants to edit
		
		if(info==1) {  //if statement if user wants to edit course name
			System.out.println("What would you like to change the name to?: ");
			input.nextLine();
			String newName=input.nextLine();  //reads in new name
			for(int x=0; x<courseList.size();x++) { //goes through courseList and finds course
				if (courseList.get(x).getCourseName().equals(courseName)) {
					courseList.get(x).setCourseName(newName);  //sets course name to the new name
				}
			}
		}
		//If statement if admin wants to change a courses id
		if(info==2) {
			System.out.println("What would you like to change the ID to?: ");
			String newID=input.next();  //reads in new ID
			for(int x=0; x<courseList.size();x++) { //goes through courseList and finds course
				if (courseList.get(x).getCourseName().equals(courseName)) {
					courseList.get(x).setCourseID(newID); // sets course ID to
															// the new ID
				}
			}
		}
		//Method that changes the max number of students
		if(info==3) {
			System.out.println("What would you like to change the maximum number of students to?: ");
			int newMax=input.nextInt();  //reads in new Max
			for(int x=0; x<courseList.size();x++) { //goes through courseList and finds course
				if (courseList.get(x).getCourseName().equals(courseName)) {
					courseList.get(x).setMaxStudents(newMax);  //sets course Max 

				}
			}
		}
		//Changes the current class size
			
		if(info==4) {
			System.out.println("What would you like to change the current number of students to?: ");
			int newCurrent=input.nextInt();  //reads in size
			for(int x=0; x<courseList.size();x++) { //goes through courseList and finds course
				if (courseList.get(x).getCourseName().equals(courseName)) {
					courseList.get(x).setCurrentStudents(newCurrent);  //sets course size to the new size

					}
				}
			}
		//Allows the admin to edit the ArrayList of Names
		if(info==5) {       
			System.out.println("What would you like to change in the list of names?(1-remove student, 2-add student): ");
			int choice=input.nextInt();
			System.out.println("Current Students");
			for(Course x: courseList) {
				if(x.getStudents()!=null) {
					for (int i = 0; i < x.getStudents().size(); i++) {
						System.out.println(x.getStudents().get(i));
					}
				}
			}
			if(choice==1) {  //If statement if admin wants to remove a name
				System.out.println("What is the name of the student you would you like to remove?");
				String studentName=input.next();
				for (Course x : courseList) {  //loops through courses 
					if (x.getCourseName().equals(courseName)) { //finds the correct course
						if(x.getStudents()!=null) {  //makes sure list isnt null
							for (int i = 0; i < x.getStudents().size(); i++) {
								if (x.getStudents().get(i).equals(studentName)) {  
									x.getStudents().remove(i); //removes the specified name
								}
							}
						}
						else {
							System.out.println("You have not added the names of the students yet.");
						}
					}
				}
			}
			if(choice==2) { //if statement if admin wants to add a name
				System.out.println("What is the name of the student you would you like to add?");
				String studentName=input.next();
				for (Course x : courseList) {
					if (x.getCourseName().equals(courseName)) {
						x.getStudents().add(studentName);  //adds name to the list
								}
							}
						}
			}
		//allows admin to change the instructor
		if(info==6) {
			System.out.println("Who would you like to change the instructor to?: ");
			input.nextLine();
			String newInstructor=input.nextLine();  //reads in new instructor
			for(int x=0; x<courseList.size();x++) { //goes through courseList and finds course
				if (courseList.get(x).getCourseName().equals(courseName)) {
					courseList.get(x).setInstructor(newInstructor);  //sets new instructor
				}
			}
		}
		//allows admin to change the course number
		if(info==7) {
			System.out.println("What would you like to change the course number to?: ");
			int newNumber=input.nextInt();  //reads in new course number
			for(int x=0; x<courseList.size();x++) { //goes through courseList and finds course
				if (courseList.get(x).getCourseName().equals(courseName)) {
					courseList.get(x).setCourseSectionNumber(newNumber);  //sets new course list
				}
			}
		}
		
		//allows the admin to change the course location
		if(info==8) {
			System.out.println("Where would you like to change the course location to?: ");
			input.nextLine();
			String newLocation=input.nextLine();  //reads in new location
			for(int x=0; x<courseList.size();x++) { //goes through courseList and finds course
				if (courseList.get(x).getCourseName().equals(courseName)) {
					courseList.get(x).setCourseLocation(newLocation);  //sets new course location
					}
				}
			}

		}
	

	@Override //method that displays the info for a specific course
	public void displayInfo(String courseID) {
		for(int x=0; x<courseList.size();x++) { //goes through courseList and finds course
			if (courseList.get(x).getCourseID().equals(courseID)) {
				System.out.println("Course Name: "+courseList.get(x).getCourseName());
				System.out.println("Course Id: " +courseList.get(x).getCourseID());
				System.out.println("Max Number of Students" +courseList.get(x).getMaxStudents());
				System.out.println("Current Number of Students: "+courseList.get(x).getCurrentStudents());
				System.out.println("List of Students"+courseList.get(x).getStudents());
				System.out.println("Course Instructor: "+courseList.get(x).getInstructor());
				System.out.println("Course Section Number: " + courseList.get(x).getCourseSectionNumber());
				System.out.println("Course location: "+courseList.get(x).getCourseLocation());
				
			}
		}
	}

	@Override  //method that registers a new student to the system
	public void registerNewStudent(String studentUsername, String studentPassword, String StudentFirstName,String StudentLastName, ArrayList studentList) {
		//creates new student object
		Student currentStudent = new Student(studentUsername, studentPassword, StudentFirstName,StudentLastName);
		studentList.add(currentStudent);		//adds student to the list
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override //method that allows admin to view all courses
	public void viewCourses() {
		for(Course x:courseList) {
			System.out.println(x.getCourseName()+","+x.getCourseID());
		}
		
	}

	@Override //method that prints out the full courses
	public void viewFullCourses() {
		for(Course x:courseList) {
			if (x.getMaxStudents() == x.getCurrentStudents()) { //checks if max student equals current students
				System.out.println(x.getCourseName());
			}
		}
	}

	@Override //writes to a file the full courses
	public void writeFullCourses() {
		try{
		    PrintWriter writer = new PrintWriter("fullcourses.txt"); //creates printwriter
		    for(Course x:courseList) {
		    	if(x.getMaxStudents()==x.getCurrentStudents()) {
		    		writer.println(x.getCourseName()); //writes to printwriter the names 
		    	}
		    }
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}

	@Override //Method that prints out the students names in a course
	public void studentsInCourse(String courseName) {
		for(Course x:courseList) {
			if (x.getCourseName().equals(courseName)) {
				for(Object y: x.getStudents()) {  //goes through the list of names
					System.out.println(y);  //prints out the names
						
					}
			}
		}
	}

	@Override  //method that checks what courses a student is in
	public void registeredCourses(String firstName, String lastName) {
		System.out.println("This student is registered for:");
		for(Course x:courseList) {
			if(x.getStudents()!=null){ //makes sure list is not null
				for(int i=0; i<x.getStudents().size(); i++) {
					if(x.getStudents().get(i).equals(firstName)) {  //checks if student is in class
					System.out.println(x.getCourseName());
				}
			}
		}
	}
		
	}

	@Override //method that sorts the list of courses based on current class size
	public void sortCourse() {
		//custom sort 
		Collections.sort(courseList, (p1, p2) -> p1.getCurrentStudents() - p2.getCurrentStudents());	
		
	}
	
	


}
