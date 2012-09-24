package ycp.edu.seniordesign.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Assignment {	
	int id;
	String name;
	int weight;
	int courseId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	//TODO: loadFrom, storeTo, equals
	/**
	 * This method can be used to load the fields of an assignment from a resultSet to an Assignment object
	 * @param resultSet the resultSet to load the fields from
	 * @throws SQLException
	 */
	public void loadFrom(ResultSet resultSet) throws SQLException {
		int index = 1;
		setId(resultSet.getInt(index++));
		setName(resultSet.getString(index++));
		setWeight(resultSet.getInt(index++));
		setCourseId(resultSet.getInt(index++));
	}
	
	/**
	 * This method can be used to store the fields from an assignment object to a prepared statement
	 * @param statement the PreparedStatement to store the fields to
	 * @throws SQLException
	 */
	 public void storeTo(PreparedStatement statement) throws SQLException {
		int index = 1;
		statement.setInt(index++, id);
		statement.setString(index++, name);
		statement.setInt(index++, weight);
		statement.setInt(index++, courseId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Assignment other = (Assignment) obj;
		return id == other.id
			&& name.equals(other.name)
			&& weight == other.weight
			&& courseId == other.courseId;
	}
}
