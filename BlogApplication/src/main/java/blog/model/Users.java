package blog.model;

/**
 * Persons is a simple, plain old java objects (POJO).
 * 
 * Persons/PersonsDao is the superclass for Administrators/AdministratorsDao and
 * BlogUsers/BlogUsersDao. Our implementation of Persons is a concrete class. This allows 
 * us to create records in the Persons MySQL table without having the associated records
 * in the Administrators or BlogUsers MySQL tables. Alternatively, Persons could be an
 * interface or an abstract class, which would force a Persons record to be created only
 * if an Administrators or BlogUsers record is created, too.
 */
public class Users {
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phone;

	public Users(String userName, String password, String firstName, String lastName, String email, String phone) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}
	
	public Users(String userName) {
		this.userName = userName;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
