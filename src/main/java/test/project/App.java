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
}
