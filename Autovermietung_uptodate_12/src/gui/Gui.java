package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import carmanagement.Car;
import carmanagement.CarDataStore;
import carmanagement.ICarDataStore;
import contractmanagement.Contract;
import contractmanagement.ContractDataStore;
import contractmanagement.IContractDataStore;
import customermanagement.Customer;
import customermanagement.CustomerDataStore;
import customermanagement.ICustomerDataStore;

/**
 * Die Klasse GUI vereint alle Subsysteme und ermöglicht das Darstellen des User-Interfaces.
 * @author Pablo Stockhausen
 * @version 1.0
 */
public class Gui extends JFrame {
	/*Felder der Panel Schnittstellen*/
	private ListCustomerPanel listcustomerpanel;
	private ListContractPanel listcontractpanel;
	private ListCarPanel listcarpanel;
	
	/**
	 * Der Konstruktor der Klasse Gui. Er enthält den Hauptanteil des Inhaltes der Klasse GUI.
	 */
	public Gui() {

		super("Mietwagenverwaltung");

		setLayout(new GridBagLayout());

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				/*----------------------------------------------------------------------*/
				/* Deklarieren */
				JTabbedPane tabpane = new JTabbedPane();
				ICustomerDataStore customerdatastore = new CustomerDataStore();
				ICarDataStore cardatastore = new CarDataStore();
				IContractDataStore contractdatastore = new ContractDataStore();
				/*----------------------------------------------------------------------*/
				/* First Customer hinzufuegen */
				customerdatastore.addCustomer(new Customer("Anne", "Meier", "Neuendorfstr", "Berlin"));
				customerdatastore.addCustomer(new Customer("Fritz", "Braun", "Baumstr", "Potsdam"));
				customerdatastore.addCustomer(new Customer("Max", "Mann", "Schoenestr", "Berlin"));
				customerdatastore.addCustomer(new Customer("Paul", "Blume", "Blumenstr", "Hamburg"));
				customerdatastore.addCustomer(new Customer("Tim", "Neu", "Irgendwasstr", "Koeln"));
				/*----------------------------------------------------------------------*/
				/* First cars hinzufuegen */
				cardatastore.addCar(new Car("BMW", "4", "Diesel", 50));
				cardatastore.addCar(new Car("Opel", "1", "Benzin", 100));
				cardatastore.addCar(new Car("Tesla", "2", "Elektro", 200));
				cardatastore.addCar(new Car("Ford", "4", "Diesel", 50));
				/*----------------------------------------------------------------------*/
				/* First Contracts hinzufuegen */
				contractdatastore.addContract(new Contract(LocalDate.now(), LocalDate.now(), "1", "0", 200));
				/*----------------------------------------------------------------------*/
				/* Panels */
				listcustomerpanel = new ListCustomerPanel(customerdatastore);
				tabpane.addTab("Customers", listcustomerpanel);
				listcarpanel = new ListCarPanel(cardatastore, contractdatastore);
				tabpane.addTab("Cars", listcarpanel);
				listcontractpanel = new ListContractPanel(contractdatastore, cardatastore, listcustomerpanel,
						listcarpanel);
				tabpane.addTab("Contracts", listcontractpanel);
				/*----------------------------------------------------------------------*/
				/* Finale Deklaration des Gridbags*/
				setLayout(new GridBagLayout());
				GridBagConstraints gc = new GridBagConstraints();
				gc.insets = new Insets(10, 10, 10, 10);
				gc.gridx = 1;
				gc.gridy = 1;
				gc.weightx = 2.0;
				gc.weighty = 1.0;
				gc.fill = GridBagConstraints.BOTH;

				getContentPane().add(tabpane, gc);
				pack();
				setLocationRelativeTo(null);
				setSize(900, 600);
				setVisible(true);

			}
		});
	}
	
	/**
	 * Main Methode für das Ausführen des Programms. Ein Objekt der Klasse Gui wird hier erstellt.
	 * @param args Die command line argumente
	 */
	public static void main(String[] args) {
		Gui oberflaeche = new Gui();

	}
}
