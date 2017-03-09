/**Ryan Cho
 * Course object class
 */
import java.util.*;

public class Course implements java.io.Serializable, Comparable<Course> {
	//holds the variables for the object
	private String courseName;
	private String courseID;
	private int maxStudents;
	private int currentStudents;
	private ArrayList<String> students = new ArrayList<>();
	private String instructor;
	private int courseSectionNumber;
	private String courseLocation;
	
	public Course(){
		
	}
	
	//constructor
	public Course (String courseName, String courseID, int maxStudents, int currentStudents, ArrayList <String> students, String instructor, int courseSectionNumber,  String courseLocation) {
		this.courseName=courseName;
		this.courseID=courseID;
		this.maxStudents=maxStudents;
		this.currentStudents=currentStudents;
		this.students=students;
		this.instructor=instructor;
		this.courseSectionNumber=courseSectionNumber;
		this.courseLocation=courseLocation;
	}

	//getters and setters
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	public int getCurrentStudents() {
		return currentStudents;
	}

	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}
	public ArrayList<String> getStudents() {
		return students;
	}
	
	public void setStudents(ArrayList<String> students) {
		this.students=students;
	}
	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getCourseSectionNumber() {
		return courseSectionNumber;
	}

	public void setCourseSectionNumber(int courseSectionNumber) {
		this.courseSectionNumber = courseSectionNumber;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) { 	
		this.courseLocation = courseLocation;
	}
	
	//method that prints out the course info
	public void viewCourseInfo(){
		System.out.println("Course Name: "+courseName);
		System.out.println("Course ID: " + courseID);
		System.out.println("Current Number of Students: " +currentStudents);
		System.out.println("Maximum Number of Students: "+ maxStudents);
		
	}

	@Override
	public int compareTo(Course o) {
		return this.courseID.compareTo(o.getCourseID());
		
	}
	
}
