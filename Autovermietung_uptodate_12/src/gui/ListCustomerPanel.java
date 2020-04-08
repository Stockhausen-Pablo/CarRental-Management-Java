package gui;

import java.awt.FlowLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import customermanagement.Customer;
import customermanagement.CustomerTableModel;
import customermanagement.ICustomerDataStore;

/**
 * Die Klasse "ListCustomerPanel" erweitert die Klasse JPanel und verknüpft alle
 * Elemente des Subsystems "customermanagement". Des Weiteren visualisiert sie
 * die Tabelle mit allen Kunden.
 * @author Pablo Stockhausen
 */
public class ListCustomerPanel extends JPanel {

	private JTable customertable;
	
	/**
	 * Der Konstruktor der Klasse "ListCustomerPanel" beinhaltet das Erstellen der
	 * Customer-Tabelle mit den Funktions-Buttons.
	 * @param customerDatastore Schnittstelle zu der Kundendatenbank.
	 */
	public ListCustomerPanel(ICustomerDataStore customerDatastore) {

		setLayout(new GridBagLayout());
		// Erstellen der Kunden-Tabelle, sowie dem hinzufügen zum Layout.
		customertable = new JTable(new CustomerTableModel(customerDatastore));
		JScrollPane tableScrollPane = new JScrollPane(customertable);
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
		 * Der RemoveButton entfernt den ausgewählten Kunden aus der Kundendatenbank.
		 */
		JButton removeButton = new JButton(new AbstractAction("Remove") {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (customertable.getSelectedRow() == -1) {
					System.out.println("Error");
				} else {
					int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Select an Option...",
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
					switch (input) {
					case 0:
						Customer tempc = customerDatastore.getCustomer(customertable.getSelectedRow());
						customerDatastore.removeCustomer(tempc);
						break;
					case 1:
						break;
					}
				}
			}
		});
		pContainer.add(removeButton);
		
		/*
		 * Der AddButton öffnet das NewCustomerFrame und fügt dem Frame ein Listener
		 * hinzu, der die Art, auf welche Weise das Fenster geschlossen wird, abfragt.
		 */
		JButton addButton = new JButton(new AbstractAction("Add...") {

			public void actionPerformed(ActionEvent arg0) {

				NewCustomerFrame dialog = new NewCustomerFrame();
				dialog.setSize(600, 300);
				dialog.setVisible(true);
				dialog.addDialogListener(new AbstractDialogListener<NewCustomerFrame>() {			
					@Override
					public void dialogOk(NewCustomerFrame dialog) {
						String firstName = dialog.getFirstName();
						String lastName = dialog.getLastName();
						String city = dialog.getCity();
						String street = dialog.getStreet();
						Customer c = new Customer(firstName, lastName, city, street);
						customerDatastore.addCustomer(c);
					}
					
					@Override
					public void dialogCanceled(NewCustomerFrame dialog) {
						System.out.println("New Customer Dialog canceled.");
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
	 * Die Methode getCustomertable() liefert uns für eine andere Klasse die Customer-Tabelle.
	 * @return customertable übergiebt die Customer-Tabelle
	 */
	public JTable getCustomertable() {
		return customertable;
	}

}
