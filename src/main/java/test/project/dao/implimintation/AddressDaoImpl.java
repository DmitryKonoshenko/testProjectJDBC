package test.project.dao.implimintation;

import test.project.dao.daoint.AddressDao;
import test.project.entity.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static test.project.App.getConnection;

public class AddressDaoImpl implements AddressDao {
	@Override
	public Address getById(Long id) {
		String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS WHERE ID=?";
		Address address = new Address();
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				address.setId(resultSet.getLong("ID"));
				address.setCountry(resultSet.getString("COUNTRY"));
				address.setCity(resultSet.getString("CITY"));
				address.setStreet(resultSet.getString("STREET"));
				address.setPostCode(resultSet.getString("POST_CODE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}

	@Override
	public void update(Address address) {
		String sql = "UPDATE ADDRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, address.getCountry());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getPostCode());
			preparedStatement.setLong(5, address.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Address address) {
		String sql = "DELETE FROM ADDRESS WHERE ID=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setLong(1, address.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
