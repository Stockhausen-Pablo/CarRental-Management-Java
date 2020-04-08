package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import calendarmanagement.ListCalendarFrame;
import carmanagement.Car;
import carmanagement.CarTableModel;
import carmanagement.ICarDataStore;
import contractmanagement.IContractDataStore;
/**
 * Die Klasse "ListCarPanel" erweitert die Klasse JPanel und verknüpft alle Elemente des Subsystems "Carmanagement".
 * Des Weiteren visualisiert sie die Tabelle mit allen Autos.
 * @author Pablo Stockhausen
 */
public class ListCarPanel extends JPanel {	
	
	//cartable ist eine leere Tabelle die wir als Grundlage für unser CarTableModel verwenden. 
	private JTable cartable;
	
	/**
	 * Der Konstruktor der Klasse "ListCarPanel" beinhaltet das erstellen der Car-Tabelle mit den Funktions-Buttons.
	 * @param cardatastore Schnittstelle zu der Autodatenbank.
	 * @param contractdatastore Schnittstelle zu der Auftragsdatenbank, für den Kalender-Button.
	 */
	public ListCarPanel(ICarDataStore cardatastore, IContractDataStore contractdatastore) {

		setLayout(new GridBagLayout());
		//Erstellen der Auto-Tabelle, sowie dem hinzufügen zum Layout
		cartable = new JTable(new CarTableModel(cardatastore));
		JScrollPane tableScrollPane = new JScrollPane(cartable);
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		add(tableScrollPane, gc);
		
		/*
		 * Die untere Button-Anordnung wurde mit einem Container gelöst. Der Container ist dem GridbagLayout hinzugefügt worden.
		 * Der Container selbst verwendent aber das Flow-Layout.
		 */		
		JPanel pContainer = new JPanel();
		pContainer.setLayout(new FlowLayout());
		
		/*
		 * Der SearchIDButton soll bei einer späteren großen Anzahl von Autos für eine einfache Suche sorgen.
		 */
		JButton SearchIDButton = new JButton(new AbstractAction("Search ID") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchCarIDFrame searchidframe = new SearchCarIDFrame();
				
				searchidframe.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						//isCancelled ist ein boolean Wert vom SeachCarIDFrame, der mir sagt wie das Programm beendet wurde.
						if(searchidframe.isCancelled() == false) {
							int carid = (int) searchidframe.getCaridfield();
							if(carid <= cardatastore.getCarCount()) {
								cartable.getSelectionModel().setSelectionInterval(carid, carid);
							}else {
								
							}
						}else {
							
						}
					}
				});	
			
				searchidframe.pack();
				searchidframe.setLocationRelativeTo(null);
				searchidframe.setSize(300, 100);
				searchidframe.setVisible(true);
			}
		});
		pContainer.add(SearchIDButton);
		
		/*
		 * KalenderButton ruft ein Kalender-Frame auf und zeigt vom ausgewählten Auto die Verfügbarkeit.
		 */
		JButton CalendarButton = new JButton(new AbstractAction("Show Calendar") {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListCalendarFrame calendarframe = new ListCalendarFrame(contractdatastore, cartable.getSelectedRow());
			}
		});
		pContainer.add(CalendarButton);

		/*
		 * Der RemoveButton entfernt das ausgewählte Auto aus der Autodatenbank.
		 */
		JButton removeButton = new JButton(new AbstractAction("Remove") {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cartable.getSelectedRow() == -1) {
					System.out.println("Error");
				} else {
					//input: yes = 0 , no = 1, deshalb der switch-case
					int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Select an Option...",
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
					switch (input) {
					case 0:
						Car tempc = cardatastore.getCar(cartable.getSelectedRow());
						cardatastore.removeCar(tempc);
						break;
					case 1:
						break;
					}
				}
			}
		});
		pContainer.add(removeButton);

		/*
		 * Der AddButton öffnet das NewCarFrame und fügt dem Frame ein Listener hinzu, der die Art, wie das Fenster geschlossen wird, abfragt.
		 */
		JButton addButton = new JButton(new AbstractAction("Add...") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewCarFrame dialog = new NewCarFrame();
				dialog.addDialogListener(new AbstractDialogListener<NewCarFrame>() {	
					@Override
					public void dialogOk(NewCarFrame dialog) {
						String carModel= dialog.getCarmodel();
						String anzSitz = dialog.getAnzSitz();
						String kraftstoff = dialog.getKraftstoff();
						int carprice = dialog.getPrice();
						Car c = new Car(carModel, anzSitz, kraftstoff, carprice);
						cardatastore.addCar(c);
					}
					
					@Override
					public void dialogCanceled(NewCarFrame dialog) {
						System.out.println("New Car Dialog canceled.");
					}
				});
				dialog.setSize(600, 300);
				dialog.setVisible(true);
			}
		});
		pContainer.add(addButton, gc);

		gc = new GridBagConstraints();
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.EAST;
		add(pContainer, gc);
	}
	
	/**
 	* Die Methode getCartable() liefert uns für eine andere Klasse die Car-Tabelle.
 	* @return cartable übergiebt die Car-Tabelle
 	*/
	public JTable getCartable() {
		return cartable;
	}
}
