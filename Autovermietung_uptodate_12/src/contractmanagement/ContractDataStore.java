package contractmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Die Klasse "ContractDataStore" implementiert den "IContractDataStore" und dient als
 * Auftragsverwaltungsdatenbank mit verschiedenen funktionalen Eigenschaften.
 * @author stock
 *
 */
public class ContractDataStore implements IContractDataStore {

	/** Die Aufträge als Map */
	private Map<String, Contract> contracts;
	
	/** Die Aufträge als Liste */
	private List<Contract> contractlist;

	/** Die Listeners */
	private Set<IContractListener> listeners;

	/**
	 * Instanziiert einen neuen ContractDataStore.
	 */
	public ContractDataStore() {
		contracts = new HashMap<>();
		contractlist = new ArrayList<>();
		listeners = new HashSet<>();
	}

	/**
	 * Fügt einen neuen Auftrag hinzu.
	 *
	 * @param contract neuer Auftrag
	 */
	@Override
	public void addContract(Contract contract) {
		contracts.put(contract.getContractid(), contract);
		contractlist.add(contract);
		for (IContractListener listener : listeners)
			listener.contractsChanged();
	}
	
	/**
	 * Entfernt einen Kunden.
	 *
	 * @param contract Ein Objekt der Klasse "Contract", dass entfernt werden soll
	 */
	@Override
	public void removeContract(Contract contract) {
		contractlist.remove(contract);
		contracts.remove(contract.getContractid(), contract);
		for (IContractListener listener : listeners)
			listener.contractsChanged();
	}

	/**
	 * Holt sich einen Auftrag auf Basis seiner ID.
	 *
	 * @param id die ID des Auftrags
	 * @return Den zu der ID passenden Auftrag
	 */
	@Override
	public Contract getContract(String id) {
		contracts.put(id, null);
		contracts.containsKey(id);
		return contracts.get(id);
	}

	/**
	 * Holt sich den Contract auf Basis seiner Position in der Liste (index)
	 *
	 * @param index der gefragte Index
	 * @return der Auftrag, an der Position des Index
	 */
	@Override
	public Contract getContract(int index) {
		return contractlist.get(index);
	}

	/**
	 * Gibt die gesamte Anzahl an Aufträgen aus.
	 *
	 * @return Anzahl als integerwert
	 */
	@Override
	public int getContractCount() {
		return contracts.size();
	}

	/**
	 * Gibt alle Aufträge als eine ArrayList aus.
	 *
	 * @return ArrayList mit allen Aufträgen
	 */
	@Override
	public Collection<Contract> getAllContracts() {
		return new ArrayList<>(contracts.values());
	}

	/**
	 * Fügt einen Contract Listener hinzu.
	 *
	 * @param listener Objekt der Schnittstelle zu dem IContractListener
	 */
	@Override
	public void addContractListener(IContractListener listener) {
		listeners.add(listener);
	}

	/**
	 * Entfernt einen CustomerListener.
	 *
	 * @param listener Objekt der Schnittstelle zu dem IContractListener
	 */
	@Override
	public void removeContractListener(IContractListener listener) {
		listeners.remove(listener);
	}

}
