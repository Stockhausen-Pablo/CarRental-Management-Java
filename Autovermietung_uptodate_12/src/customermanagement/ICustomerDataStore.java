package customermanagement;

import java.util.Collection;

/**
 * Das Interface "ICustomerDataStore" dient als Schnittstelle für die Kundendatenbank und liefert uns verschiedene Funktionen.
 * 
 * @author Pablo Stockhausen
 *
 */
public interface ICustomerDataStore {

	/**
	 * Fügt einen neuen Kunden hinzu.
	 *
	 * @param customer neuer Kunde
	 */
	public void addCustomer(Customer customer);

	/**
	 * Entfernt einen Kunden.
	 *
	 * @param customer Ein Objekt der Klasse "Customer", dass entfernt werden soll
	 */
	public void removeCustomer(Customer customer);

	/**
	 * Holt sich einen Kunden auf Basis seiner ID.
	 *
	 * @param id die ID des Kunden
	 * @return Den zu der ID passenden Kunden
	 */
	public Customer getCustomer(String id);

	/**
	 * Holt sich den Customer auf Basis seiner Position in der Liste (index)
	 *
	 * @param index der gefragte Index
	 * @return der Kunde, an der Position des Index
	 */
	public Customer getCustomer(int index);

	/**
	 * Gibt die gesamte Anzahl an Kunden aus.
	 *
	 * @return Anzahl als integerwert
	 */
	public int getCustomerCount();

	/**
	 * Gibt alle Kunden als eine ArrayList aus.
	 *
	 * @return ArrayList mit allen Kunden
	 */
	public Collection<Customer> getAllCustomers();

	/**
	 * Fügt einen CustomerListener hinzu.
	 *
	 * @param listener Objekt der Schnittstelle zu dem ICustomerListener
	 */
	public void addCustomerListener(ICustomerListener listener);

	/**
	 * Entfernt einen CustomerListener.
	 *
	 * @param listener Objekt der Schnittstelle zu dem ICustomerListener
	 */
	public void removeCustomerListener(ICustomerListener listener);

}
