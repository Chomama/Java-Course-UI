/**Ryan Cho
 * Interface that holds the methods used in the Admin Class
 * 
 */

import java.util.ArrayList;

public interface AdminInterface {
	
	public void createNewCourse(String courseName, String courseID, int maxStudents, int currentStudents, ArrayList <String> students, String instructor, int courseSectionNumber,  String courseLocation);
	public void deleteCourse(String courseName);
	public void editCourse(String courseName);
	public void displayInfo(String courseID);
	public void registerNewStudent(String studentUsername, String studentPassword, String StudentFirstName,String StudentLastName, ArrayList studentList);
	public void exit();
	public void viewCourses();
	public void viewFullCourses();
	public void writeFullCourses();
	public void studentsInCourse(String courseName);
	public void registeredCourses(String firstName, String lastName);
	public void sortCourse();
	
		
	
}
