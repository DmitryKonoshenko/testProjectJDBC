package test.project.dao;

import test.project.dao.daoint.AddressDao;
import test.project.dao.daoint.PersonDao;
import test.project.dao.implimintation.AddressDaoImpl;
import test.project.dao.implimintation.PersonDaoImpl;

public class DaoLocatorImpl implements DaoLocator {
	@Override
	public PersonDao getPersonDao() {
		return new PersonDaoImpl();
	}

	@Override
	public AddressDao getAddressDao() {
		return new AddressDaoImpl();
	}
}
