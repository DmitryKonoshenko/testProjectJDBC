package test.project.dao.daoint;

import test.project.entity.Person;

import java.util.List;

public interface PersonDao {
	/**
	 * Add person to DB
	 *
	 * @param person - person
	 */
	void add(Person person);

	/**
	 * Get all person's from DB
	 *
	 * @return List<Person>
	 */
	List<Person> getAll();

	/**
	 * Get person by name
	 *
	 * @param name - name of person
	 * @return Person
	 */
	Person getByName(String name);

	/**
	 * Update person
	 *
	 * @param person - person
	 */
	void update(Person person);

	/**
	 * Delete person
	 *
	 * @param address - address
	 */
	void remove(Person address);

}