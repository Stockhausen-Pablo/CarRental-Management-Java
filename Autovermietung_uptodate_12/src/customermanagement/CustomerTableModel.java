package customermanagement;

import javax.swing.table.AbstractTableModel;


/**
 * Die Klasse "CustomerTableModel" dient nach dem Model-View-Control-Pattern, als Darstellungsschicht
 * unserer Kundendaten.
 * 
 * @author Gruppe12
 *
 */
public class CustomerTableModel extends AbstractTableModel {

	/** Die Spalten Titel */
	private static String[] COLUMN_TITLES = new String[] { "First Name", "Last Name", "Street", "City" };

	/** Unsere Kundendatenbank */
	private ICustomerDataStore customerDatastore;

	/**
	 * Instanziiert eine neues CustomerTableModel.
	 *
	 * @param customerdatastore Schnittstelle zu dem ICustomerDataStore
	 */
	public CustomerTableModel(ICustomerDataStore customerdatastore) {
		this.customerDatastore = customerdatastore;
		customerDatastore.addCustomerListener(new ICustomerListener() {

			public void customersChanged() {
				fireTableStructureChanged();
			}

		});
	}

	/**
	 * Holt sich die Spaltenanzahl.
	 *
	 * @return vier Spalten als Integerwert.
	 */
	@Override
	public int getColumnCount() {
		return 4;
	}

	/**
	 * Holt sich die Zeilenanzahl.
	 *
	 * @return Die Kundenanzahl entspricht der Zeilenanzahl
	 */
	@Override
	public int getRowCount() {
		return customerDatastore.getCustomerCount();
	}

	/**
	 * Die Value an einem bestimmten Punkt.
	 *
	 * @param row Die Zeile
	 * @param col Die Spalte
	 * @return Den Wert
	 */
	@Override
	public Object getValueAt(int row, int col) {
		Object ret = null;
		Customer customer = customerDatastore.getCustomer(row);
		switch (col) {
		case 0:
			ret = customer.getFirstName();
			break;
		case 1:
			ret = customer.getLastName();
			break;
		case 2:
			ret = customer.getStreet();
			break;
		case 3:
			ret = customer.getCity();
		}
		return ret;
	}

	/**
	 * Überprüft ob die "Cell" editierbar ist.
	 *
	 * @param rowIndex der Zeilenindex
	 * @param columnIndex der Spaltenindex
	 * @return true, wenn die "Cell" editierbar ist
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	/**
	 * Belegt die Value an einem gewissen Punkt.
	 *
	 * @param aValue der aWert eines Objektes
	 * @param rowIndex Zeilenindex
	 * @param columnIndex Spaltenindex
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Customer customer = customerDatastore.getCustomer(rowIndex);

		switch (columnIndex) {
		case 0:
			customer.setFirstName((String) aValue);
			break;
		case 1:
			customer.setLastName((String) aValue);
			break;
		case 2:
			customer.setStreet((String) aValue);
			break;
		case 3:
			customer.setCity((String) aValue);
			break;
		}
	}

	/**
	 * Holt sich die Spaltennamen.
	 *
	 * @param column die jeweilige Spalte
	 * @return der Spaltenname
	 */
	@Override
	public String getColumnName(int column) {
		return COLUMN_TITLES[column];
	}

}
