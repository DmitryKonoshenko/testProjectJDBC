package test.project.dao.daoint;

import test.project.entity.Address;

public interface AddressDao {
	/**
	 * Get Address by id
	 *
	 * @param id - address id
	 * @return Address
	 */
	Address getById(Long id);

	/**
	 * Update address by id
	 *
	 * @param address - address
	 */
	void update(Address address);

	/**
	 * Delete address
	 *
	 * @param address - address
	 */
	void remove(Address address);
}
