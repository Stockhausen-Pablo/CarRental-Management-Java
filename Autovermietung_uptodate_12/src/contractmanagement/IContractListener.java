package contractmanagement;


/**
 * Dieses Listener interface ist zum erhalten von IContract events gedacht.
 * Eine Klasse die daran interessiert ist, IContract Events zu verarbeiten,
 * implementiert dieses interface mithilfe der Methode "addIContractListener".
 * Wenn das IContract event auftritt, wird die spezifische implementierung der Methode
 * ausgef�hrt.
 * @author Pablo Stockhausen
 */
public interface IContractListener {

	/**
	 * Die Auftr�ge haben sich in irgendeiner Weise ver�ndert. 
	 */
	public void contractsChanged();
	
}
