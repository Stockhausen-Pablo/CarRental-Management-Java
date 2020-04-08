package calendarmanagement;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePickerSettings;

import contractmanagement.IContractDataStore;


/**
 * Die Klasse "ListCalendarFrame" erweitert die Klasse JFrame und bietet uns die Möglichkeit,
 * eine grafische Kalendardarstellung von der Verfügbarkeit bestimmter Fahrzeuge zu erhalten.
 * 
 * @author Gruppe12
 *
 */
public class ListCalendarFrame extends JFrame {

	/**
	 * Instanziiert ein neues ListCalendarFrame.
	 *
	 * @param contractdatastore Schnittstelle zu der Auftragsdatenbank.
	 * @param selectedrow Die ausgewählte Zeile im ListCarPanel
	 */
	public ListCalendarFrame(IContractDataStore contractdatastore, int selectedrow) {
		
		super("Avaibility for Car " + selectedrow);
		
		getContentPane().setLayout(new GridBagLayout());	
		
		CalendarPanel carpanel = new CalendarPanel();
		CarHighlightPolicy pol = new CarHighlightPolicy(contractdatastore, selectedrow);
		DatePickerSettings settings = new DatePickerSettings();
		settings.setHighlightPolicy(pol);
		carpanel.setSettings(settings);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill= GridBagConstraints.BOTH;
		getContentPane().add(carpanel, gc);

		setSize(600,300);
		setVisible(true);
	}
}
