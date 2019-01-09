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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({App.class, AddressDaoImpl.class})
public class AddressDaoImplTest {
	@InjectMocks
	private AddressDaoImpl addressDao;

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Long id = 1L;
	private Address address;

	@Before
	public void init() throws SQLException {
		connection = PowerMockito.mock(Connection.class);
		preparedStatement = PowerMockito.mock(PreparedStatement.class);
		resultSet = PowerMockito.mock(ResultSet.class);
		PowerMockito.mockStatic(App.class);
		PowerMockito.when(App.getConnection()).thenReturn(connection);
		PowerMockito.when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
		PowerMockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
		PowerMockito.when(resultSet.next()).thenReturn(true);

		address = new Address();
		address.setCity("Voronej");
		address.setCountry("RUS");
		address.setPostCode("123123");
		address.setStreet("Milk");
		address.setId(1L);
	}

	@Test
	public void getById() throws SQLException {
		PowerMockito.when(resultSet.getLong("ID")).thenReturn(1L);
		PowerMockito.when(resultSet.getString("COUNTRY")).thenReturn("Bel");
		PowerMockito.when(resultSet.getString("CITY")).thenReturn("Bobr");
		PowerMockito.when(resultSet.getString("STREET")).thenReturn("Fer");
		PowerMockito.when(resultSet.getString("POST_CODE")).thenReturn("123");
		Address address = addressDao.getById(1L);
		assertEquals(address.getId(), id);
	}

	@Test
	public void update() {
		addressDao.update(address);
	}

	@Test
	public void remove() {
		addressDao.remove(address);
	}

}