package test.project.dao.implimintation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import test.project.App;
import test.project.entity.Address;
import test.project.entity.Person;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({App.class, PersonDaoImpl.class})
public class PersonDaoImplTest {
	@InjectMocks
	PersonDaoImpl personDao;

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Statement statement;
	private Long id = 1L;
	private Long id2 = 0L;
	private Person person;
	private Address address;
	private LocalDate br = LocalDate.of(1995, 3, 9);


	private final String personSql = "INSERT INTO PERSON (FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID) " +
			"VALUES(?, ?, ?, ?)";
	private final String addressSql = "INSERT INTO ADDRESS (COUNTRY, CITY, STREET, POST_CODE) VALUES(?, ?, ?, ?)";

	@Before
	public void init() throws SQLException {
		connection = PowerMockito.mock(Connection.class);
		preparedStatement = PowerMockito.mock(PreparedStatement.class);
		resultSet = PowerMockito.mock(ResultSet.class);
		statement = PowerMockito.mock(Statement.class);
		PowerMockito.mockStatic(App.class);
		PowerMockito.when(App.getConnection()).thenReturn(connection);
		PowerMockito.when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
		PowerMockito.when(connection.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
		PowerMockito.when(connection.prepareStatement(addressSql, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
		PowerMockito.when(connection.createStatement()).thenReturn(statement);
		PowerMockito.when(statement.executeQuery(any(String.class))).thenReturn(resultSet);
		PowerMockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
		PowerMockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
		PowerMockito.when(preparedStatement.executeUpdate()).thenReturn(1);
		PowerMockito.when(resultSet.getDate("BIRTHDAY")).thenReturn(Date.valueOf(br));

		address = new Address();
		address.setCity("Voronej");
		address.setCountry("RUS");
		address.setPostCode("123123");
		address.setStreet("Milk");
		address.setId(id);

		person = new Person();
		person.setFirstName("Denchik");
		person.setLastName("Por");
		person.setBirthday(br);
		person.setAddress(address);
		person.setId(id);
	}

	@Test
	public void add() throws SQLException {
		PowerMockito.when(resultSet.next()).thenReturn(false);
		personDao.add(person);
	}

	@Test
	public void getAll() throws SQLException {
		PowerMockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
		List<Person> list = personDao.getAll();
		assertEquals(list.get(0).getId(), id2);
	}

	@Test
	public void getByName() throws SQLException {
		PowerMockito.when(resultSet.next()).thenReturn(true);
		Person person = personDao.getByName("Denchik");
		assertEquals(person.getId(), id2);
	}

	@Test
	public void update() throws SQLException {
		personDao.update(person);
	}

	@Test
	public void remove() throws SQLException {
		personDao.remove(person);
	}
}