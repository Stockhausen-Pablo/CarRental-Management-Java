package customermanagement;

/**
 * Dieses Listener interface ist zum erhalten von ICustomer events gedacht.
 * Eine Klasse die daran interessiert ist, ICustomer Events zu verarbeiten,
 * implementiert dieses interface mithilfe der Methode "addICustomerListener".
 * Wenn das ICustomer event auftritt, wird die spezifische implementierung der Methode
 * ausgef�hrt.
 *
 */
public interface ICustomerListener {

	/**
	 * Die Kunden haben sich in irgendeiner Weise ver�ndert. 
	 */
	public void customersChanged();

}
