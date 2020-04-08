package carmanagement;


import javax.swing.table.AbstractTableModel;

/**
 * Die Klasse "CarTableModel" dient nach dem Model-View-Control-Pattern, als Darstellungsschicht
 * unserer Fahrzeugdaten.
 * 
 * @author Gruppe12
 *
 */
public class CarTableModel extends AbstractTableModel {
	
	/** Die Spalten Titel */
	private static String[] COLUMN_TITLES = new String[] { "Model", "Number of Seats", "Fuel", "Price per Day" };
	
	/** Unsere Fahrzeugdatenbank */
	private ICarDataStore cardatastore;

	/**
	 * Instanziiert eine neues CarTableModel.
	 *
	 * @param cardatastore Schnittstelle zu dem ICarDataStore
	 */
	public CarTableModel(ICarDataStore cardatastore) {
		this.cardatastore = cardatastore;
		cardatastore.addCarListener(new ICarListener() {

			@Override
			public void carsChanged() {
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
		// TODO Auto-generated method stub
		return 4;
	}

	/**
	 * Holt sich die Zeilenanzahl.
	 *
	 * @return Die Fahrzeuganzahl entspricht der Zeilenanzahl
	 */
	@Override
	public int getRowCount() {
		return cardatastore.getCarCount();
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
		Car car = cardatastore.getCar(row);
		switch (col) {
		case 0:
			ret = car.getCarModel();
			break;
		case 1:
			ret = car.getAnzSitze();
			break;
		case 2:
			ret = car.getKraftstoff();
			break;
		case 3:
			ret = car.getCarprice();
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
		Car car = cardatastore.getCar(rowIndex);

		switch (columnIndex) {
		case 0:
			car.setCarModel((String) aValue);
			break;
		case 1:
			car.setAnzSitze((String) aValue);
			break;
		case 2:
			car.setKraftstoff((String) aValue);
			break;
		case 3:
			car.setCarprice((int) aValue);
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
