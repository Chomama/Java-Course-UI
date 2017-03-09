
/**Ryan Cho
 * Main Program that calls all of the methods and holds most of the inputs.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Main implements java.io.Serializable {
	//placeholder variables
	static ArrayList<Course> courseList = new ArrayList<>();
	static ArrayList<Student> studentList = new ArrayList<>();
	static String userType;
	static String userName;
	static String password;
	static Student currentStudent=null;
	static Scanner input = new Scanner(System.in); // scanner object

	public static void main(String[] args) throws FileNotFoundException {
		// reads in the lists from the serialized files
		initialize();
		
		//calls method that allows the user to login either as an admin or student
		login();
		Collections.sort(courseList);
		for(Course x:courseList) {
			System.out.print(x.getCourseName()+",");
		}

		while (true) { //while loop that keeps going until user exits
			if(userType.equals("Admin") || userType.equals("admin")){  //Initiates when user logs in as Admin
				Admin Admin001 = new Admin(userName, password, "Bob", "Saggot", courseList);  //creates the Admin Object
				System.out.println("Type 1 for Course Management Menu and 2 for Reports Menu: ");  //allows admin to choose menu
				int menuType = input.nextInt();
	
				if (menuType == 1) {  //brings up the menu for course management options
					System.out.println("Enter the number for the option you want.");
					System.out.println("1.Create a new course");
					System.out.println("2.Delete a course");
					System.out.println("3.Edit a course");
					System.out.println("4.Display information for a given course");
					System.out.println("5.Register a student");
					System.out.println("6.Exit");
					int task = input.nextInt();
	
					if (task == 1) {  //Initiates when user chooses to create a new course
						System.out.println("Enter the course name: ");  //Admin inputs all info necessary for course constructor
						input.nextLine();
						String courseName = input.nextLine();
						System.out.println("Enter the course ID");
						String courseID = input.next();
						System.out.println("Enter the maximum number of students: ");
						int max = input.nextInt();
						System.out.println("Enter the current number of students: ");
						int current = input.nextInt();
						System.out.println("Enter the names of the students seperated by commas: ");
						ArrayList<String> names = new ArrayList<>();
						input.nextLine();
						for (String x : input.nextLine().split(",")) {  //adds the names from the input to the ArrayList
							names.add(x);
						}
						System.out.println("Enter the instructors name: ");
						String courseInstructor = input.next();
						System.out.println("Enter the course section number: ");
						int courseNumber = input.nextInt();
						System.out.println("Enter the course location: ");
						input.nextLine();
						String courseLocation = input.nextLine();
						Admin001.createNewCourse(courseName, courseID, max, current, names, courseInstructor, courseNumber,
								courseLocation);  //creates the new course object
					}
					else if (task == 2) {  //allows Admin to delete a course
						System.out.println("Enter the name of the course you would like to delete: ");
						input.nextLine();
						String courseName = input.nextLine();
						Admin001.deleteCourse(courseName);  //cals method that deletes course
					} 
					else if (task == 3) {  //allows Admin to edit anything in the course
						System.out.println("Enter the name of the course you would like to edit: ");
						input.nextLine();
						String courseName = input.nextLine();
						Admin001.editCourse(courseName);  //calls method that holds everything to edit a course
					}
					else if (task == 4) {  //allows admin to view details of a specific course
						System.out.println("Enter the course ID: ");
						String courseID = input.next();
						Admin001.displayInfo(courseID);  //calls the display method that prints out the course info
					}
					else if (task == 5) {  //allows Admin to register a new student to the system
						//inputs for the necessary inputs for the new student constructor
						System.out.println("Type in a username:");
						String studentUsername = input.next();
						System.out.println("Type in a password:");
						String studentPassword = input.next();
						System.out.println("Type in your first name:");
						String StudentFirstName = input.next();
						System.out.println("Type in a your last name:");
						String StudentLastName = input.next();
						//calls method that creates a new student object and adds it to the studentList
						Admin001.registerNewStudent(studentUsername, studentPassword, StudentFirstName, StudentLastName, studentList);
						
	
					} 
					else if (task == 6) {  //exits
						saveState();  //method that serializes the updated lists
						break;  //breaks out of the program
					}
					
				}
				if (menuType==2) {  //reports menu
					System.out.println("Enter the number for the option you want.");
					System.out.println("1.View all courses");
					System.out.println("2.View all courses that are full");
					System.out.println("3.Write to file courses that are full");
					System.out.println("4.View names of students being registered in a specific course");
					System.out.println("5.View the list of courses that a student is being registered for");
					System.out.println("6.Sort courses based on current class size");
					System.out.println("7. Exit");
					int task = input.nextInt();
					
					if(task==1) {  //allows admin to view the courses
						Admin001.viewCourses();
					}
					if(task==2) {  //allows admin to view the courses that are full
						Admin001.viewFullCourses();
					}
					if(task==3) {  //allows the admin to write to a file the courses that are full
						Admin001.writeFullCourses();
					}
					if(task==4) {  //allows the admin to view the students that are registered in a course
						System.out.println("Please type the name of the course you would like to view the student's names:");
						input.nextLine();
						String courseName=input.nextLine();
						Admin001.studentsInCourse(courseName);
						
					}
					if(task==5) {  //allows the admin to view the courses that a student is registered for
						System.out.println("Please enter the first name of the student you would like to view the courses for:");
						String firstName=input.next();
						System.out.println("Please enter the last name of the stuednt you would like to view the courses for:");
						String lastName=input.next();
						Admin001.registeredCourses(firstName, lastName);
					}
					if(task==6) {
						Admin001.sortCourse();  //calls method that sorts the courses based on the class size
					}
					if(task==7) {
						saveState();  //serializes the updated lists
						break;
						}
					}
				}
		
	
			//initates options for student user
			if (userType.equals("Student") || userType.equals("student")) {  
				//prints out the menu
				System.out.println("Enter the number for the option you want.");
				System.out.println("1.View all courses");
				System.out.println("2.View open courses");
				System.out.println("3.Register for a course");
				System.out.println("4.Withdraw from a course");
				System.out.println("5.View current courses");
				System.out.println("6.Exit");
				int task = input.nextInt();

				 if(task==1) {  //allows student to view all of the courses
					 currentStudent.viewAllCourses(courseList);
				 }
				 if(task==2) {  //allows student to view all of the courses that are still open
					 currentStudent.viewOpenCourses(courseList);
				 }
				 if(task==3) {  //registers a student to a class
					 System.out.println("Please enter the course name:");
					 input.nextLine();
					 String courseName=input.nextLine();
					 System.out.println("Please enter the course section number:");
					 int courseSection=input.nextInt();
					 System.out.println("Please enter your first name:");
					 String firstName=input.next();
					 System.out.println("Please enter your last name:");
					 String lastName=input.next();
					 //calls register method which adds the student to a class
					 currentStudent.register(courseName, courseSection, firstName, lastName, courseList);
				 }
				 if(task==4) {  //allows student to withdraw from a class
					 System.out.println("Please enter the course name:");
					 input.nextLine();
					 String courseName=input.nextLine();
					 System.out.println("Please enter your first name:");
					 String firstName=input.next();
					 currentStudent.withdraw(courseName, firstName, courseList);  //calls method with removes student from a course
					 
				 }
				 if(task==5) {  //calls method which shows which courses student is registered for
					 currentStudent.viewRegisteredClasses(currentStudent, courseList);
				 }
				 if(task==6) {  //serializes the updated lists
					 saveState();
					 break;
					 
				 }
				 

			}
		}
}


	// loads serialized data
	private static void initialize() throws FileNotFoundException {
		// serialization that reads in the courseList
		try {
			FileInputStream fis = new FileInputStream("courseList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			courseList = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			Courses.loadCourses();  //if file does not exist previously calls method which loads new one
			courseList = Courses.courseList;  //sets courseList to courseList with info from csv

		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}

		// serialization that reads in the studentList

		try {
			FileInputStream fis2 = new FileInputStream("studentList.ser");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			studentList = (ArrayList) ois2.readObject();
			ois2.close();
			fis2.close();
		} catch (IOException ioe) {
			Student.serialize(); //if file does not exist calls method which creates new file with  serializes studentList
			studentList = Student.studentList;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
	}

	// logs in the user
	private static void login() {
		System.out.println("Are you a Student or Admin: "); // asks user for
															// user type
		userType = input.next();
		while (!userType.equals("Admin") && !userType.equals("Student")) { // invalid
																			// inputs
			System.out.println("Invalid Input. Are you a Student or Admin: ");
			input.nextLine();
			userType = input.nextLine();
		}
		//makes sure admin username and password is correct
		if (userType.equals("Admin") || userType.equals("admin")) { 
			System.out.println("What is your username?: ");
			userName = input.next();
			System.out.println("What is your password?: ");
			password = input.next();
			while (!userName.equals("Admin") || !password.equals("Admin001")) {
				System.out.println("Incorrect Username or Password.");
				System.out.println("What is your username?: ");
				userName = input.next();
				System.out.println("What is your password?: ");
				password = input.next();
			}
		}
		//makes sure that the student is entering the right username and password for a registered student
		if (userType.equals("Student") || userType.equals("student")){
			System.out.println("What is your username?: ");
			userName = input.next();
			System.out.println("What is your password?: ");
			password = input.next();
			//calls method which finds the student within the system and sets currentStudent to that student
			currentStudent=Student.findStudent(userName, password, studentList);  
		}
	}

	//method that serializes the updated lists
	private static void saveState() {
		try {
			FileOutputStream fileOut = new FileOutputStream("courseList.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(courseList);

			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		try {
			FileOutputStream fileOut = new FileOutputStream("studentList.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(studentList);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
			for (Course x : courseList) {
				System.out.println(x.getCourseName());

			}
		}
	}
}
