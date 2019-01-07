package test.project.dao.implimintation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.project.dao.daoint.AddressDao;
import test.project.entity.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static test.project.App.getConnection;

public class AddressDaoImpl implements AddressDao {
	private static final Log LOG = LogFactory.getLog(AddressDaoImpl.class);
	@Override
	public Address getById(Long id) {
		LOG.debug("Adding address in DB");
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
			LOG.warn("Adding address in DB failed: "+e);
		}
		return address;
	}

	@Override
	public void update(Address address) {
		LOG.debug("Updating address in DB");
		String sql = "UPDATE ADDRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, address.getCountry());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getPostCode());
			preparedStatement.setLong(5, address.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.warn("Updating address in DB filed: " + e);
		}
	}

	@Override
	public void remove(Address address) {
		LOG.debug("Removing address from DB");
		String sql = "DELETE FROM ADDRESS WHERE ID=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setLong(1, address.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.warn("Removing address from DB failed: "+e);
		}
	}
}
