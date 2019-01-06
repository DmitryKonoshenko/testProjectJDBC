package test.project.dao.implimintation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.project.dao.DaoLocatorFactory;
import test.project.dao.daoint.PersonDao;
import test.project.entity.Address;
import test.project.entity.Person;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static test.project.App.getConnection;

public class PersonDaoImpl implements PersonDao {

	private static final Log LOG = LogFactory.getLog(PersonDaoImpl.class);

	public PersonDaoImpl() {
	}

	@Override
	public void add(Person person) {
		LOG.debug("Adding person into DB");
		String personSql = "INSERT INTO PERSON (FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID) " +
				"VALUES(?, ?, ?, ?)";
		String addressSql = "INSERT INTO ADDRESS (COUNTRY, CITY, STREET, POST_CODE) VALUES(?, ?, ?, ?)";
		try (PreparedStatement preparedStatementPerson = getConnection().prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS);
		     PreparedStatement preparedStatementAddress = getConnection().prepareStatement(addressSql, Statement.RETURN_GENERATED_KEYS)) {
			getConnection().setAutoCommit(false);

			preparedStatementAddress.setString(1, person.getAddress().getCountry());
			preparedStatementAddress.setString(2, person.getAddress().getCity());
			preparedStatementAddress.setString(3, person.getAddress().getStreet());
			preparedStatementAddress.setString(4, person.getAddress().getPostCode());
			preparedStatementAddress.executeUpdate();

			try (ResultSet rs = preparedStatementAddress.getGeneratedKeys()) {
				if (rs.next()) {
					person.getAddress().setId(rs.getLong(1));
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}

			preparedStatementPerson.setString(1, person.getFirstName());
			preparedStatementPerson.setString(2, person.getLastName());
			preparedStatementPerson.setDate(3, java.sql.Date.valueOf(person.getBirthday()));
			preparedStatementPerson.setLong(4, person.getAddress().getId());
			preparedStatementPerson.executeUpdate();

			try (ResultSet rs = preparedStatementPerson.getGeneratedKeys()) {
				if (rs.next()) {
					person.setId(rs.getLong(1));
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
			getConnection().commit();
			getConnection().close();
		} catch (SQLException e) {
			LOG.warn("Adding person into DB failed: " + e);
		}
	}

	@Override
	public List<Person> getAll() {
		LOG.debug("Getting all person from DB");
		List<Person> employeeList = new ArrayList<>();

		String sql = "SELECT FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID FROM PERSON";

		try (Statement statement = getConnection().createStatement();
		     ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				Person person = new Person();
				setPerson(resultSet, person);
				Address address = new Address();
				address.setId(resultSet.getLong("ADDRESS_ID"));
				address.setLazyLoad(true);
				person.setAddress(address);
				employeeList.add(person);
			}
			getConnection().close();
		} catch (SQLException e) {
			LOG.debug("Getting all person from DB failed: " + e);
		}
		return employeeList;
	}

	private void setPerson(ResultSet resultSet, Person person) throws SQLException {
		person.setId(resultSet.getLong("ID"));
		person.setFirstName(resultSet.getString("FIRST_NAME"));
		person.setLastName(resultSet.getString("LAST_NAME"));
		person.setBirthday(resultSet.getDate("BIRTHDAY").toLocalDate());
	}

	@Override
	public Person getByName(String name) {
		LOG.debug("Getting person from DB by name");
		String sql = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID FROM PERSON WHERE FIRST_NAME=?";
		Person person = new Person();
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			connection.setAutoCommit(false);
			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				setPerson(resultSet, person);
				Address address = DaoLocatorFactory.getDaoLocator().getAddressDao().getById(resultSet.getLong("ADDRESS_ID"));
				person.setAddress(address);
			}
			getConnection().close();
		} catch (SQLException e) {
			LOG.debug("Getting person from DB by name failed: " + e);
		}
		return person;
	}

	@Override
	public void update(Person person) {
		String sql = "UPDATE PERSON SET FIRST_NAME=?, LAST_NAME=?, BIRTHDAY=?, ADDRESS_ID=? WHERE id=?";
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			getConnection().setAutoCommit(false);
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setDate(3, Date.valueOf(person.getBirthday()));
			preparedStatement.setLong(4, person.getAddress().getId());
			preparedStatement.setLong(5, person.getId());

			DaoLocatorFactory.getDaoLocator().getAddressDao().update(person.getAddress());
			preparedStatement.executeUpdate();
			getConnection().commit();
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void remove(Person person) {
		String sql = "DELETE FROM PERSON WHERE ID=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			getConnection().setAutoCommit(false);
			preparedStatement.setLong(1, person.getId());
			preparedStatement.executeUpdate();

			DaoLocatorFactory.getDaoLocator().getAddressDao().remove(person.getAddress());

			getConnection().commit();
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
