package test.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Address {
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String country;
	@Getter
	@Setter
	private String city;
	@Getter
	@Setter
	private String street;
	@Getter
	@Setter
	private String postCode;
	@Getter
	@Setter
	private boolean lazyLoad;

	public Address() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Address)) return false;
		Address address = (Address) o;
		return id.equals(address.id) &&
				Objects.equals(country, address.country) &&
				Objects.equals(city, address.city) &&
				Objects.equals(street, address.street) &&
				Objects.equals(postCode, address.postCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, country, city, street, postCode);
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", country='" + country + '\'' +
				", city='" + city + '\'' +
				", street='" + street + '\'' +
				", postCode='" + postCode + '\'' +
				'}';
	}
}
