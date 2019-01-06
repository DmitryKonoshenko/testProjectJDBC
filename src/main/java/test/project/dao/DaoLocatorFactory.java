package test.project.dao;

public class DaoLocatorFactory {
	/**
	 * The dao locator.
	 */
	private static DaoLocator daoLocator;
	/**
	 * Gets the dao locator.
	 *
	 * @return the dao locator
	 */
	public static DaoLocator getDaoLocator() {
		if (daoLocator == null) {
			daoLocator = new DaoLocatorImpl();
		}

		return daoLocator;
	}
}
