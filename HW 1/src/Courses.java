/**Ryan Cho

 * This class holds the methods for the courses and also holds the arraylist of course objects.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Courses implements java.io.Serializable {
	// Initiates Arraylist that holds course objects
	static ArrayList<Course> courseList = new ArrayList<>(); 

	public static void main(String[] args) throws FileNotFoundException {
		loadCourses(); //calls method that reads from csv
		
		try //reads in the serialized courseList
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
		for(Course x: courseList) {
			System.out.println(x.getCourseName());
		}
	   }
	

	public static void loadCourses() throws FileNotFoundException {
		// Creates new scanner to read in csv file
		Scanner scanner = new Scanner(new File("MyUniversityCourses.csv")); 
		//sets delimeter to newline
		scanner.useDelimiter("/n");
		//skips the first line which is the header in the csv
		scanner.nextLine();
		//while loop that goes until there is no more text in the csv
		while (scanner.hasNextLine()) {
			//sets string line to the next line in the file
			String line = scanner.nextLine();
			//splits each line by where the commas are
			String[] columns = line.split(",");
			//sets courseName to the text before the first comma
			String courseName = columns[0];
			// sets courseID to the second sectio 
			String courseID = columns[1];
			// sets max students to the 3 section
			int maxStudents = Integer.parseInt(columns[2]);
			// sets currentStudents to the 4 section
			int currentStudents = Integer.parseInt(columns[3]);
			// sets Arraylist to null on default and keeps it at null if section 5 is null
			ArrayList<String> students = new ArrayList<>();
			// sets instructor to section 6
			String instructor = columns[5];
			// sets course number to section 7
			int courseSectionNumber = Integer.parseInt(columns[6]);
			// sets location to column 8
			String courseLocation = columns[7];
			// creates a new course object with the course info in the line
			Course course = new Course(courseName, courseID, maxStudents, currentStudents, students, instructor,
					courseSectionNumber, courseLocation);
			//adds the course to the list of courses
			courseList.add(course);
		}
		//closes the scanner
		scanner.close();
		
	}

	
	//method that prints out the information about the courses
	public static void viewCourses() {
		for (int i = 0; i < courseList.size(); i++) {
			courseList.get(i).viewCourseInfo();

		}
	}

	
}

