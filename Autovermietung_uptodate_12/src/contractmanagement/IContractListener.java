package contractmanagement;


/**
 * Dieses Listener interface ist zum erhalten von IContract events gedacht.
 * Eine Klasse die daran interessiert ist, IContract Events zu verarbeiten,
 * implementiert dieses interface mithilfe der Methode "addIContractListener".
 * Wenn das IContract event auftritt, wird die spezifische implementierung der Methode
 * ausgeführt.
 * @author Pablo Stockhausen
 */
public interface IContractListener {

	/**
	 * Die Aufträge haben sich in irgendeiner Weise verändert. 
	 */
	public void contractsChanged();
	
}
