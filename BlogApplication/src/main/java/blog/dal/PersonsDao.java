package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.model.*;


public class PersonsDao {
	protected BlogConnectionManager connectionManager;
	
	private static PersonsDao instance = null;
	protected PersonsDao() {
		connectionManager = new BlogConnectionManager();
	}
	public static PersonsDao getInstance() {
		if(instance == null) {
			instance = new PersonsDao();
		}
		return instance;
	}

	public Persons create(Persons person) throws SQLException {
		String insertPerson = "INSERT INTO Persons(UserName,FirstName,LastName) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);
			insertStmt.setString(1, person.getUserName());
			insertStmt.setString(2, person.getFirstName());
			insertStmt.setString(3, person.getLastName());
			insertStmt.executeUpdate();
			
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Persons updateLastName(Persons person, String newLastName) throws SQLException {
		String updatePerson = "UPDATE Persons SET LastName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newLastName);
			updateStmt.setString(2, person.getUserName());
			updateStmt.executeUpdate();
			
			person.setLastName(newLastName);
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public Persons delete(Persons person) throws SQLException {
		String deletePerson = "DELETE FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setString(1, person.getUserName());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Persons getPersonFromUserName(String userName) throws SQLException {
		String selectPerson = "SELECT UserName,FirstName,LastName FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Persons person = new Persons(resultUserName, firstName, lastName);
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Persons> getPersonsFromFirstName(String firstName) throws SQLException {
		List<Persons> persons = new ArrayList<Persons>();
		String selectPersons =
			"SELECT UserName,FirstName,LastName FROM Persons WHERE FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersons);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Persons person = new Persons(userName, resultFirstName, lastName);
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return persons;
	}
}
