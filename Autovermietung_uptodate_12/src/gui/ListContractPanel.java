package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import carmanagement.ICarDataStore;
import contractmanagement.Contract;
import contractmanagement.ContractTableModel;
import contractmanagement.IContractDataStore;

/**
 * Die Klasse "ListContractPanel" erweitert die Klasse JPanel und verknüpft alle
 * Elemente des Subsystems "contractmanagement". Des Weiteren visualisiert sie
 * die Tabelle mit allen Aufträgen.
 * @author Pablo Stockhausen
 */
public class ListContractPanel extends JPanel {

	private final JTable contracttable;
	
	// Die filterecontractlist benötigen wir für das Erstellen eines Auftrages
	// mithilfe eines Validierungsprozess.
	private Collection<Contract> filteredcontractlist;

	/**
	 * Der Konstruktor der Klasse "ListContractPanel" beinhaltet das Erstellen der
	 * Contract-Tabelle mit den Funktions-Buttons.
	 * 
	 * @param contractDatastore Schnittstelle zu der Auftragsdatenbank.
	 * @param carDatastore      Schnittstelle zu der Autodatenbank, da wir den Preis
	 *                          kalkulieren müssen.
	 * @param listcustomerpanel Ein Objekt des ListCustomerPanel, dass wir benötigen
	 *                          um ein ListSelectionListener hinzuzufügen.
	 * @param listcarpanel      Ein Objekt des ListCarPanel, dass wir benötigen um
	 *                          ein ListSelectionListener hinzuzufügen.
	 */
	public ListContractPanel(IContractDataStore contractDatastore, ICarDataStore carDatastore,
			ListCustomerPanel listcustomerpanel, ListCarPanel listcarpanel) {

		setLayout(new GridBagLayout());
		
		// Erstellen der Auftrags-Tabelle, sowie dem hinzufügen zum Layout
		contracttable = new JTable(new ContractTableModel(contractDatastore));
		JScrollPane tableScrollPane = new JScrollPane(contracttable);
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		add(tableScrollPane, gc);

		/*
		 * Die untere Button-Anordnung wurde mit einem Container gelöst. Der Container
		 * ist dem GridbagLayout hinzugefügt worden. Der Container selbst verwendent
		 * aber das Flow-Layout.
		 */
		JPanel pContainer = new JPanel();
		pContainer.setLayout(new FlowLayout());

		/*
		 * Der RemoveButton entfernt den ausgewählten Auftrag aus der Auftragsdatenbank.
		 */
		JButton removeButton = new JButton(new AbstractAction("Remove") {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (contracttable.getSelectedRow() == -1) {
					System.out.println("Error");
				} else {
					int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Select an Option...",
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
					switch (input) {
					case 0:
						Contract tempc = contractDatastore.getContract(contracttable.getSelectedRow());
						contractDatastore.removeContract(tempc);
						break;
					case 1:
						break;
					}
				}
			}
		});
		pContainer.add(removeButton);
		
		/*
		 * Der AddButton öffnet das NewContractFrame und fügt dem Frame ein Listener
		 * hinzu, der die Art, in welchem Zustand sich das Fenster befindet, abfragt.
		 */
		JButton addButton = new JButton(new AbstractAction("Add...") {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewContractFrame dialog = new NewContractFrame();
				dialog.setAlwaysOnTop(true);
				dialog.addWindowListener(new WindowAdapter() {
					
					//Das Fenster wurde geöffnet und wir fügen dem customerpanel die ListSelectionListener hinzu.
					@Override
					public void windowOpened(WindowEvent e) {
						listcustomerpanel.getCustomertable().getSelectionModel()
								.addListSelectionListener(new ListSelectionListener() {

									@Override
									public void valueChanged(ListSelectionEvent e) {
										dialog.setCustomeridfield(
												"" + listcustomerpanel.getCustomertable().getSelectedRow());
									}
								});
						listcarpanel.getCartable().getSelectionModel()
								.addListSelectionListener(new ListSelectionListener() {

									@Override
									public void valueChanged(ListSelectionEvent e) {
										dialog.setCaridfield("" + listcarpanel.getCartable().getSelectedRow());
									}
								});
					}
					
					//Das Fenster wurde geschlossen und wir beginnen mit dem Validierungsprozess.
					@Override
					public void windowClosed(WindowEvent e) {
						/* First If - Wie das Frame beendet wurde */
						if (dialog.isCancelled() == true) {
							System.out.println("abgebrochen mit cancel button");
						} else if (dialog.isCancelled() == false) {
							// Überprüft ob der ausgewählte Zeitraum in der Vergangenheit liegt.
							if (atLeastOneInThePast(dialog.getFrom(), dialog.getUntil())) {
								JOptionPane.showMessageDialog(null,
										"Please only enter Dates in the present. Adding a new Contract was unsuccessful.",
										"Warning", JOptionPane.WARNING_MESSAGE);

							} else {
								/* Liste mit allen Contracts holen und filtern nach der ausgewählten carid */
								filteredcontractlist = contractDatastore.getAllContracts();
								for (Iterator<Contract> it = filteredcontractlist.iterator(); it.hasNext();) {
									if (!it.next().getCarid().contains("" + dialog.getCaridfield())) {

										it.remove();
									}
								}
								ArrayList<Contract> newList = new ArrayList<Contract>(filteredcontractlist);
								/*
								 * 2nd if - Abgleich mit der Contract Datenbank und überprüfen, ob diese
								 * Zeiträume schon belegt sind.
								 */
								if (newList.size() > 0) {
									/* Exception handling bei der Datum eingabe */
									for (int i = 0; i < newList.size(); i++) {
										
										//Zeitraum ist identisch mit einem anderen Auftrag. Das muss man extra prüfen, da "isOverlapping" nicht genau ist.
										if (isIdentical(newList.get(i).getFrom(), newList.get(i).getUntil(),
												dialog.getFrom(), dialog.getUntil()) == true) {
											JOptionPane.showMessageDialog(null,
													"Adding a new contract was not successful. Please check the car tab for the Calendar Button to see the avaibility of the cars.",
													"Notification", JOptionPane.WARNING_MESSAGE);
											break;
										} else {
											if (isOverlapping(newList.get(i).getFrom(), newList.get(i).getUntil(),
													dialog.getFrom(), dialog.getUntil()) == true) {
												JOptionPane.showMessageDialog(null,
														"Adding a new contract was not successful. Please check the car tab for the Calendar Button to see the avaibility of the cars.",
														"Notification", JOptionPane.WARNING_MESSAGE);
												System.out.println("Overlapped");
												break;
												
											//Wenn isOverlapping ist false, dann können wir ein Auftrag erstellen.
											} else if (isOverlapping(newList.get(i).getFrom(),
													newList.get(i).getUntil(), dialog.getFrom(),
													dialog.getUntil()) == false) {

												if (i == (newList.size() - 1)) {
													//Zeitraum des Auftrages, benötigen wir für die Preiskalkulation da der Preis pro Tag ist
													long pdays = ChronoUnit.DAYS.between(dialog.getFrom(),
															dialog.getUntil());
													int pdaysint = (int) pdays
															* carDatastore.getCar(dialog.getCaridfield()).getCarprice();
													contractDatastore.addContract(new Contract(dialog.getFrom(),
															dialog.getUntil(), "" + dialog.getCustomeridfield(),
															"" + dialog.getCaridfield(), pdaysint));
													JOptionPane.showMessageDialog(null,
															"Adding the Contract was successful.", "Confirmation",
															JOptionPane.INFORMATION_MESSAGE);
												}
											}
										}
									}

								} else {
									/*
									 * Diesem Else-Zweig folgt man, wenn es noch kein Car Auftrag mit der ID gibt -> 
									 * newList size == 0, somit können wir direkt den Auftrag speichern.
									 */
									long pdays = ChronoUnit.DAYS.between(dialog.getFrom(), dialog.getUntil());
									int pdaysint = (int) pdays
											* carDatastore.getCar(dialog.getCaridfield()).getCarprice();
									contractDatastore.addContract(new Contract(dialog.getFrom(), dialog.getUntil(),
											"" + dialog.getCustomeridfield(), "" + dialog.getCaridfield(), pdaysint));
									JOptionPane.showMessageDialog(null, "Adding the Contract was successful.",
											"Confirmation", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
						;
					}

				});
			}
		});
		pContainer.add(addButton, gc);

		gc = new GridBagConstraints();
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.EAST;
		add(pContainer, gc);
	}

	/**
	 * Die Methode "atLeastOneInThePast" überprüft, ob einer von den beiden Daten in der Vergangenheit liegt. 
	 * @param from Datumangabe von wann
	 * @param until Datumangabe bis wann
	 * @return true oder false
	 */
	private static boolean atLeastOneInThePast(LocalDate from, LocalDate until) {
		LocalDate today = LocalDate.now();
		return today.isAfter(from) || today.isAfter(until) || until.isBefore(from);
	}
	
	/**
	 * Die Methode" isOverlapping" überprüft, ob zwei Zeiträume überlappen.
	 * @param start1 Anfangsdatum vom ersten Zeitraum.
	 * @param end1	Enddatum vom ersten Zeitraum.
	 * @param start2 Anfangsdatum vom zweiten Zeitraum.
	 * @param end2 Enddatum vom zweiten Zeitraum.
	 * @return true oder false
	 */
	private static boolean isOverlapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
		return start1.isBefore(end2) && start2.isBefore(end1);
	}
	
	/**
	 * Die Methode "isIdentical" ist eine Ergänzung zu der isOverlapping-Methode. "IsIdentical" dient dazu, gleiche Zeiträume zu identifizieren,
	 * da isBefore oder isAfter Probleme mit gleichen dates hat.
	 * @param start1
	 * @param end1
	 * @param start2
	 * @param end2
	 * @return true or false
	 */
	private static boolean isIdentical(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
		if ((start1.isEqual(start2) && end1.isEqual(end2))
				|| (start1.isEqual(start2) || end1.isEqual(end2) || end2.isEqual(start1) || end1.isEqual(start2))) {
			return true;
		} else {
			return false;
		}
	}
}
