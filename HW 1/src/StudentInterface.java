/**Ryan Cho
 * Interface that holds the methods for the Student Object
 * 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public interface StudentInterface extends java.io.Serializable{
	public void viewAllCourses(ArrayList<Course> courseList);  
	public void viewOpenCourses(ArrayList<Course> courseList);
	public void register(String courseName, int courseSection, String firstName, String lastName, ArrayList<Course> courseList);
	public void withdraw(String courseName, String firstName, ArrayList<Course> courseList);
	public void viewRegisteredClasses(Student currrentStudent,ArrayList<Course> courseList);
}
