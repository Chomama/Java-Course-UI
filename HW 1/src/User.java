/**Ryan Cho
 * 
 * Superclass for user type that Admin and Student fall under.
 * 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class User implements java.io.Serializable{
	//Variables that both Admin and Student use
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	//basic user constructor 
	public User(String username, String password, String firstName, String lastName){
		this.username=username;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	//Getters and Setters for private variables
	public String getUsername() {
		return username;
	}
	public void setUsername(String p){
		this.username=username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastname){
		this.lastName=lastName;
	}
}
