package test.project.dao;

import test.project.dao.daoint.AddressDao;
import test.project.dao.daoint.PersonDao;

public interface DaoLocator {

	/**
	 * Gets the person dao.
	 *
	 * @return the person dao
	 */
	PersonDao getPersonDao();

	/**
	 * Gets the address dao.
	 *
	 * @return the address dao
	 */
	AddressDao getAddressDao();

}
