package contractmanagement;

import javax.swing.table.AbstractTableModel;

/**
 * Die Klasse "ContractTableModel" dient nach dem Model-View-Control-Pattern, als Darstellungsschicht
 * unserer Auftragsdaten.
 * @author Gruppe12
 *
 */
public class ContractTableModel extends AbstractTableModel {

	/** Die Spalten Titel */
	private static String[] COLUMN_TITLES = new String[] { "From", "Until", "CustomerID", "CarID", "Endprice" };
	
	/** Unsere Auftragsdatenbank */
	private IContractDataStore contractDatastore;

	/**
	 * Instanziiert eine neues ContractTableModel.
	 *
	 * @param contractdatastore Schnittstelle zu dem IContractDataStore
	 */
	public ContractTableModel(IContractDataStore contractdatastore) {
		this.contractDatastore = contractdatastore;
		contractDatastore.addContractListener(new IContractListener() {

			public void contractsChanged() {
				fireTableStructureChanged();
			}

		});
	}

	/**
	 * Holt sich die Spaltenanzahl.
	 *
	 * @return fünf Spalten als Integerwert.
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	/**
	 * Holt sich die Zeilenanzahl.
	 *
	 * @return Die Auftragsanzahl entspricht der Zeilenanzahl
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return contractDatastore.getContractCount();
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
		Contract contract = contractDatastore.getContract(row);
		switch (col) {
		case 0:
			ret = contract.getFrom();
			break;
		case 1:
			ret = contract.getUntil();
			break;
		case 2:
			ret = contract.getCustomerid();
			break;
		case 3:
			ret = contract.getCarid();
			break;
		case 4:
			ret = contract.getEndprice();
		}
		return ret;
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
