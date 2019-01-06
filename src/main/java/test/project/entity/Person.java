package test.project.entity;

import lombok.Getter;
import lombok.Setter;
import test.project.dao.DaoLocatorFactory;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String firstName;
	@Getter
	@Setter
	private String lastName;
	@Getter
	@Setter
	private LocalDate birthday;

	@Setter
	private Address address;

	public Address getAddress() {
		if (!address.isLazyLoad()) return address;

		address = DaoLocatorFactory.getDaoLocator().getAddressDao().getById(address.getId());
		return address;
	}

	public Person() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;
		Person person = (Person) o;
		return getId().equals(person.getId()) &&
				getFirstName().equals(person.getFirstName()) &&
				getLastName().equals(person.getLastName()) &&
				Objects.equals(getBirthday(), person.getBirthday()) &&
				Objects.equals(getAddress(), person.getAddress());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getFirstName(), getLastName(), getBirthday(), getAddress());
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthday=" + birthday +
				", address=" + address +
				'}';
	}
}
