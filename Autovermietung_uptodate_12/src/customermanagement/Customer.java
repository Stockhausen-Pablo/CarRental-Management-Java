package customermanagement;

import java.util.UUID;

/**
 * Die Klasse "Customer" ist unser Kundenelement, dass ¸ber verschiedene Attribute verf¸gt.
 *
 * @author Gruppe12
 */
public class Customer {
	
	/** Der Vorname. */
	private String firstName;
	
	/** Der Nachname. */
	private String lastName;
	
	/** Die Straﬂe */
	private String street;
	
	/** Die Stadt. */
	private String city;
	
	/** Die ID. */
	private String id;

	/**
	 * Instanziiert einen neuen Kunden.
	 */
	public Customer() {
		id = UUID.randomUUID().toString();
	}

	/**
	 * Instanziiert einen neuen Kunden.
	 *
	 * @param firstName der Vornname
	 * @param lastName der Nachname
	 * @param street die Straﬂe
	 * @param city die Stadt
	 */
	public Customer(String firstName, String lastName, String street, String city) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
	}

	/**
	 * Instanziiert einen neuen Kunden.
	 *
	 * @param original ‹berschreibt das Original eines Kunden.
	 */
	public Customer(Customer original) {
		id = original.id;
		firstName = original.firstName;
		lastName = original.lastName;
		street = original.street;
		city = original.city;
	}

	/**
	 * Holt sich den Vornnamen.
	 *
	 * @return der Vornname
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Belegt den Vornamen.
	 *
	 * @param firstName der neue Vorname
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Holt sich den Nachnamen.
	 *
	 * @return der Nachname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Belegt den Nachnamen.
	 *
	 * @param lastName der neue Nachname
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Holt sich die Straﬂe.
	 *
	 * @return die Straﬂe
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Belegt die Straﬂe.
	 *
	 * @param street die neue Straﬂe
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	
	/**
	 * Holt sich die Stadt
	 *
	 * @return die Stadt
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Belegt die Stadt
	 *
	 * @param city die neue Stadt
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Holt sich die ID
	 *
	 * @return die ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Belegt die ID
	 *
	 * @param id die neue ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Aus der ID berechneter HashCode.
	 *
	 * @return Hashwert als int
	 */
	public int hashCode() {
		return id.hashCode();
	}

	/**
	 * Boolean-Abfrage ob ein Objekte einem anderen gleich ist.
	 * Aufgrundlage seiner ID.
	 *
	 * @param obj Ein Objekt der Klasse "Customer"
	 * @return true, wenn erfolgreich
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			Customer otherCustomer = (Customer) obj;
			return id.equals(otherCustomer.getId());
		}
		return false;
	}

	/**
	 * Einfach toString()-Methode der unser Objekt "Customer" als String ausgiebt.
	 *
	 * @return Ein String mit allen Attributen bis auf die ID
	 */
	@Override
	public String toString() {
		return this.firstName + ": " + this.lastName + ": " + this.street + ": " + this.city;
	}
}
