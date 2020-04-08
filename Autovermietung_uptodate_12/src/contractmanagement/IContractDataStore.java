package contractmanagement;

import java.util.Collection;

/**
 * Das Interface "IContractDataStore" dient als Schnittstelle f�r die Auftragsdatenbank und liefert uns verschiedene Funktionen.
 * @author Gruppe12
 *
 */
public interface IContractDataStore {
	
	/**
	 * F�gt einen neuen Auftrag hinzu.
	 *
	 * @param contract neuer Auftrag
	 */
	public void addContract(Contract contract);
	
	/**
	 * Entfernt einen Kunden.
	 *
	 * @param contract Ein Objekt der Klasse "Contract", dass entfernt werden soll
	 */
	public void removeContract(Contract contract);
	
	/**
	 * Holt sich einen Auftrag auf Basis seiner ID.
	 *
	 * @param id die ID des Auftrags
	 * @return Den zu der ID passenden Auftrag
	 */
	public Contract getContract(String id);
	
	/**
	 * Holt sich den Contract auf Basis seiner Position in der Liste (index)
	 *
	 * @param index der gefragte Index
	 * @return der Auftrag, an der Position des Index
	 */
	public Contract getContract(int index);
	
	/**
	 * Gibt die gesamte Anzahl an Auftr�gen aus.
	 *
	 * @return Anzahl als integerwert
	 */
	public int getContractCount();
	
	/**
	 * Gibt alle Auftr�ge als eine ArrayList aus.
	 *
	 * @return ArrayList mit allen Auftr�gen
	 */
	public Collection<Contract> getAllContracts();
	
	/**
	 * F�gt einen Contract Listener hinzu.
	 *
	 * @param listener Objekt der Schnittstelle zu dem IContractListener
	 */
	public void addContractListener(IContractListener listener);
	
	/**
	 * Entfernt einen CustomerListener.
	 *
	 * @param listener Objekt der Schnittstelle zu dem IContractListener
	 */
	public void removeContractListener(IContractListener listener);
	
}
