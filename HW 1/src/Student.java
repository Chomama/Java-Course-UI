/**Ryan Cho
 * Class for Student Object
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Student extends User implements StudentInterface{
	static ArrayList<Course> courseList = new ArrayList<>(); 
	static ArrayList<Student> studentList=new ArrayList<>();

	public static void main(String[]args) {
		//reads in the serialized courseList
		try
        {
            FileInputStream fis = new FileInputStream("courseList.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
         }catch(IOException ioe){
             ioe.printStackTrace();
             return;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return;
          }
	}
	
	
	//student constructor
	public Student(String username, String password, String firstName, String lastName) {
		super(username,password,firstName, lastName);
	}
	
	@Override //allows the student to view the courses
	public void viewAllCourses(ArrayList<Course> courseList) {
		for(Course x: courseList) {
			System.out.println(x.getCourseName());
		}
		System.out.println();
	}

	@Override //allows the student to check courses that are not full
	public void viewOpenCourses(ArrayList<Course> courseList) {
		for(int x=0; x<courseList.size();x++) {
			//checks if the course is not full by comparing max to current
			if (courseList.get(x).getMaxStudents() != courseList.get(x).getCurrentStudents()) {
				System.out.println(courseList.get(x).getCourseName());
			}
		}
		System.out.println();
	}

	//method that registers student to a class
	public void register(String courseName, int courseSection, String firstName, String lastName, ArrayList<Course> courseList) {
		for(Course x:courseList) {
			//makes sure that it is adding to the right course
			if(x.getCourseName().equals(courseName) && x.getCourseSectionNumber()==courseSection){
				x.getStudents().add(firstName); //adds student to the course
			}
			if(x.getStudents().contains(firstName)){  //prints if student is succesfully added
				System.out.println("You succesfully registered for"+ x.getCourseName());
			}
		}
	}
	
	@Override //removes student from a course
	public void withdraw(String courseName, String firstName, ArrayList<Course> courseList) {
		for(Course x:courseList) { //loops through the courses and finds the specified one
			if(x.getCourseName().equals(courseName)) {
				for(int i=0; i<x.getStudents().size(); i++) {
					if(x.getStudents().get(i).equals(firstName)){ 
						x.getStudents().remove(i); //removes the student
					}
					
				}
			}
		}
	}

	@Override  //allows student to view the courses he/she is registered on
	public void viewRegisteredClasses(Student currentStudent, ArrayList<Course> courseList) {
		System.out.println("You are currently registered for: ");
		for(Course x:courseList) {
			if(x.getStudents()==null) { //checks if not registered
				System.out.println("You are currently not registered for any classes.");
				System.out.println();
				return;
			}
			else{ //if student is found in a class 
				for(int i=0; i<x.getStudents().size(); i++) {
					if(x.getStudents().get(i).equals(currentStudent.getFirstName())){
						System.out.println(x.getCourseName()); //prints the course
						System.out.println();
				}
				}
			}
		}
	}

	
	//method that creates the file for the serialized studentList 
	public static void serialize() {
		try {
	         FileOutputStream fileOut = new FileOutputStream("studentList.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(studentList);
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	//Method that is used when logging in in the main
	public static Student findStudent(String userName, String password, ArrayList<Student> studentList) {
		Student currentStudent=null; //placeholder variable
		for(Student x: studentList) { 
			if(x.getUsername().equals(userName)) { //finds the correct student through username
				currentStudent= x; //sets the current student to the found student
			}
			}
		while(currentStudent==null) { //while loop that iterates while incorrect username or password
			Scanner input=new Scanner(System.in); //prompts for username and password
			System.out.println("Invalid username or password.");
			System.out.println("What is your username?: ");
			userName = input.next();
			System.out.println("What is your password?: ");
			password = input.next();
			for(Student x: studentList) {
				if(x.getUsername().equals(userName) && x.getPassword().equals(password)) {
					currentStudent= x;  //sets currentstudent if correct information is entered
				}
			}
		}
		return currentStudent; //returns the right student
		}
	}
	


