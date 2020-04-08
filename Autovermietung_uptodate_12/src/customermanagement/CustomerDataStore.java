package customermanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Die Klasse "CustomerDataStore" implementiert den "ICustomerDataStore" und dient als
 * Kundenverwaltungsdatenbank mit verschiedenen funktionalen Eigenschaften.
 * 
 * @author Pablo Stockhausen
 *
 */
public class CustomerDataStore implements ICustomerDataStore {

	/** Die Kunden als Map */
	private Map<String, Customer> customers;
	
	/** Die Kunden als Liste */
	private List<Customer> customerlist;

	/** Die Listeners */
	private Set<ICustomerListener> listeners;

	/**
	 * Instanziiert einen new CustomerDataStore
	 */
	public CustomerDataStore() {
		customers = new HashMap<>();
		customerlist = new ArrayList<>();
		listeners = new HashSet<>();
	}

	/**
	 * Fügt einen neuen Kunden hinzu.
	 *
	 * @param customer neuer Kunde
	 */
	@Override
	public void addCustomer(Customer customer) {
		customers.put(customer.getId(), customer);
		customerlist.add(customer);
		for (ICustomerListener listener : listeners)
			listener.customersChanged();
	}

	/**
	 * Holt sich einen Kunden auf Basis seiner ID.
	 *
	 * @param id die ID des Kunden
	 * @return Den zu der ID passenden Kunden
	 */
	@Override
	public Customer getCustomer(String id) {
		customers.put(id, null);
		customers.containsKey(id);
		return customers.get(id);
	}

	/**
	 * Gibt alle Kunden als eine ArrayList aus.
	 *
	 * @return ArrayList mit allen Kunden
	 */
	@Override
	public Collection<Customer> getAllCustomers() {

		return new ArrayList<>(customers.values());

	}

	/**
	 * Holt sich den Customer auf Basis seiner Position in der Liste (index)
	 *
	 * @param index der gefragte Index
	 * @return der Kunde, an der Position des Index
	 */
	@Override
	public Customer getCustomer(int index) {
		return customerlist.get(index);
	}

	/**
	 * Gibt die gesamte Anzahl an Kunden aus.
	 *
	 * @return Anzahl als integerwert
	 */
	@Override
	public int getCustomerCount() {
		return customers.size();
	}

	/**
	 * Fügt einen neuen CustomerListener hinzu.
	 *
	 * @param listener Objekt der Schnittstelle zu dem ICustomerListener
	 */
	@Override
	public void addCustomerListener(ICustomerListener listener) {
		listeners.add(listener);
	}

	/**
	 * Entfernt einen CustomerListener.
	 *
	 * @param listener Objekt der Schnittstelle zu dem ICustomerListener
	 */
	@Override
	public void removeCustomerListener(ICustomerListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Entfernt einen Kunden.
	 *
	 * @param customer Ein Objekt der Klasse "Customer", dass entfernt werden soll
	 */
	@Override
	public void removeCustomer(Customer customer) {
		customerlist.remove(customer);
		customers.remove(customer.getId(), customer);
		for (ICustomerListener listener : listeners)
			listener.customersChanged();
	}

}
