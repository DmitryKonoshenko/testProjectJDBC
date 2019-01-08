package test.project.dao.implimintation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import test.project.App;
import test.project.entity.Address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({App.class, AddressDaoImpl.class})
public class AddressDaoImplTest {

	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";
	private String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS WHERE ID=?";
	@InjectMocks private AddressDaoImpl addressDao;

	@Test
	public void getById() throws SQLException {
		MockitoAnnotations.initMocks(this);
		Connection connection = PowerMockito.mock(Connection.class);
		PreparedStatement preparedStatement = PowerMockito.mock(PreparedStatement.class);
		ResultSet resultSet = PowerMockito.mock(ResultSet.class);
		PowerMockito.mockStatic(App.class);
		PowerMockito.when(App.getConnection()).thenReturn(connection);
		PowerMockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
		PowerMockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
		PowerMockito.when(resultSet.next()).thenReturn(true);
		PowerMockito.when(resultSet.getLong("ID")).thenReturn(1L);
		PowerMockito.when(resultSet.getString("COUNTRY")).thenReturn("Bel");
		PowerMockito.when(resultSet.getString("CITY")).thenReturn("Bobr");
		PowerMockito.when(resultSet.getString("STREET")).thenReturn("Fer");
		PowerMockito.when(resultSet.getString("POST_CODE")).thenReturn("123");
		Address address = addressDao.getById(1L);
		System.out.println(address);
	}

}