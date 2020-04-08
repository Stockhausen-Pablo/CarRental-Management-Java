package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

/**
 * Die Klasse "NewContractFrame" erweitert die Klasse JFrame und bietet eine grafische Oberfläche zur 
 * Erstellung eines neuen Auftrages. Außerdem kann die Klasse bestimmte Listener benachrichtigen.
 * @author Pablo Stockhausen
 *
 */
public class NewContractFrame extends JFrame {

	private DatePicker from;
	private DatePicker until;
	private JTextField customeridfield;
	private JTextField caridfield;

	/*
	 * Ich habe hier nicht mit den AbstractDialogListener gearbeitet, sondern mit einem einfachen
	 * boolean Wert der mir sagt, wie das Fenster geschlossen wurde.
	 */
	private boolean cancelled = true;

	/**
	 * Instanziiert ein NewContractFrame
	 */
	public NewContractFrame() {

		super("New Contract");

		getContentPane().setLayout(new GridBagLayout());

		JLabel fromlabel = new JLabel("From:");
		GridBagConstraints gc = new GridBagConstraints();
		getContentPane().add(fromlabel, gc);

		from = new DatePicker();
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(from, gc);

		JLabel tolabel = new JLabel("Until:");
		gc = new GridBagConstraints();
		gc.gridx = 2;
		getContentPane().add(tolabel, gc);

		until = new DatePicker();
		gc = new GridBagConstraints();
		gc.gridx = 3;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(until, gc);

		JLabel customeridlabel = new JLabel("Customer ID:");
		gc = new GridBagConstraints();
		gc.gridy = 1;
		getContentPane().add(customeridlabel, gc);

		customeridfield = new JTextField();
		customeridfield.setText("");
		customeridfield.setEditable(false);
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1.0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(customeridfield, gc);

		JLabel caridlabel = new JLabel("Car id:");
		gc = new GridBagConstraints();
		gc.gridy = 2;
		getContentPane().add(caridlabel, gc);

		caridfield = new JTextField();
		caridfield.setEditable(false);
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1.0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(caridfield, gc);

		JButton cancelbutton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelled = true;
				dispose();

			}
		});

		cancelbutton.setText("CANCEL");
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 0.0;
		gc.anchor = GridBagConstraints.WEST;
		getContentPane().add(cancelbutton, gc);

		JButton addbutton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Überprüft hier schon, ob irgendein Feld leer ist.
				if (customeridfield.getText().equals("") || caridfield.getText().equals("") || from.getDate() == null
						|| until.getDate() == null) {
					System.out.println("Leeres Feld");
				} else {
					cancelled = false;
					dispose();
				}
			}
		});
		addbutton.setText("Add Contract");
		gc = new GridBagConstraints();
		gc.gridx = 3;
		gc.gridy = 3;
		gc.weightx = 0.0;
		gc.anchor = GridBagConstraints.EAST;
		getContentPane().add(addbutton, gc);

		pack();
		setLocationRelativeTo(null);
		setSize(600, 300);
		setVisible(true);

	}
	
	/**
	 * Indikator ob der Erstellungsprozess abgebrochen wurde oder nicht.
	 * 
	 * @return true oder false je nachdem.
	 */
	public boolean isCancelled() {
		return cancelled;
	}
	
	//Ab hier nur Getter und Setter.

	/**
	 * Die Methode "getFrom()" gibt das Startdatum des datepickers from wieder.
	 * 
	 * @return Anfangsdatum als LocalDate.
	 */
	public LocalDate getFrom() {
		return from.getDate();
	}
	
	/**
	 * Die Methode "getUntil()" gibt das Enddatum des datepickers until wieder.
	 * 
	 * @return Enddatum als LocalDate.
	 */
	public LocalDate getUntil() {
		return until.getDate();
	}
	
	/**
	 * Die Methode "getCustomeridfield()" gibt den Inhalt des jtextfields customeridfield wieder.
	 * 
	 * @return String des customeridfield als Integerwert.
	 */
	public int getCustomeridfield() {
		int temp = Integer.parseInt(customeridfield.getText());
		return temp;	
	}
	
	/**
	 * Die Methode "getCaridfield()" gibt den Inhalt des jtextfields caridfield wieder.
	 * 
	 * @return String des caridfield als Integerwert.
	 */
	public int getCaridfield() {
		int temp = Integer.parseInt(caridfield.getText());
		return temp;
	}
	
	/**
	 * Methode um den Inhalt des customeridfields neu zu belegen.
	 * 
	 * @param customeridfield Der Text-Inhalt wird neu belegt.
	 */
	public void setCustomeridfield(String customeridfield) {
		this.customeridfield.setText(customeridfield);
	}

	/** 
	 * Methode um den Inhalt des caridfields neu zu belegen.
	 * 
	 * @param caridfield Der Text-Inhalt wird neu belegt
	 */
	public void setCaridfield(String caridfield) {
		this.caridfield.setText(caridfield);
	}
}
