package carmanagement;

/**
 * Dieses Listener interface ist zum erhalten von ICar events gedacht.
 * Eine Klasse die daran interessiert ist, ICar Events zu verarbeiten,
 * implementiert dieses interface mithilfe der Methode "addICarListener".
 * Wenn das ICar event auftritt, wird die spezifische implementierung der Methode
 * ausgeführt.
 *
 */
public interface ICarListener {
	
	/**
	 * Die Fahrzeuge haben sich in irgendeiner Weise verändert. 
	 */
	public void carsChanged();
	
}
