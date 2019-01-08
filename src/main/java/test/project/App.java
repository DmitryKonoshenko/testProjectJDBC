package test.project;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.project.dao.DaoLocatorFactory;
import test.project.dao.daoint.PersonDao;
import test.project.dao.implimintation.PersonDaoImpl;
import test.project.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
	private static Connection connection;
	private static final Log LOG = LogFactory.getLog(App.class);
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName(DB_DRIVER);
				connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				LOG.debug("Connection OK");
			} catch (ClassNotFoundException | SQLException e) {
				LOG.warn("Connection ERROR: " + e);
			}
		}
		return connection;
	}

	public static void main(String[] args) throws SQLException {
		PersonDao personDao = new PersonDaoImpl();
//		Address address = new Address();
//		address.setCity("Voronej");
//		address.setCountry("RUS");
//		address.setPostCode("123123");
//		address.setStreet("Milk");
//
//		Person person = new Person();
//		person.setFirstName("Denchik");
//		person.setLastName("Por");
//		person.setBirthday(LocalDate.of(1995,3,9));
//		person.setAddress(address);

//		Person person2 = new Person();
//		person2.setFirstName("Denchik");
//		person2.setLastName("Por2");
//		person2.setBirthday(LocalDate.of(1995,3,9));
//		person2.setAddress(address);
//
//		personDao.add(person);
//		personDao.add(person2);
//		List<Person> p = DaoLocatorFactory.getDaoLocator().getPersonDao().getAll();
//		p.forEach(System.out::println);
//		System.out.println(personDao.getByName("Denchik"));

//		Address address = new Address();
//		address.setId(1L);
//		address.setCity("Piter");
//		address.setCountry("Her");
//		address.setPostCode("123123");
//		address.setStreet("Milk");
//
//		Person person = new Person();
//		person.setId(1L);
//		person.setFirstName("Mark");
//		person.setLastName("Cot");
//		person.setBirthday(LocalDate.of(1996, 3, 9));
//		person.setAddress(address);
//		personDao.update(person);
		Person person = DaoLocatorFactory.getDaoLocator().getPersonDao().getByName("Denchik");
		personDao.remove(person);
	}
}
