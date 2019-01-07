package test.project.dao.daoint;

import test.project.entity.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao {
	/**
	 * Add person to DB
	 *
	 * @param person - person
	 */
	void add(Person person) throws SQLException;

	/**
	 * Get all person's from DB
	 *
	 * @return List<Person>
	 */
	List<Person> getAll() throws SQLException;

	/**
	 * Get person by name
	 *
	 * @param name - name of person
	 * @return Person
	 */
	Person getByName(String name) throws SQLException;

	/**
	 * Update person
	 *
	 * @param person - person
	 */
	void update(Person person) throws SQLException;

	/**
	 * Delete person
	 *
	 * @param address - address
	 */
	void remove(Person address) throws SQLException;

}