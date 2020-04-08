package gui;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Die Klasse "NewCustomerFrame" erweitert die Klasse JFrame und bietet eine grafische Oberfläche zur 
 * Erstellung eines neuen Kunden. Außerdem kann die Klasse bestimmte Listener benachrichtigen.
 * @author Gruppe12
 *
 */
public class NewCustomerFrame extends JFrame {

	private JTextField firstnamefield;
	private JTextField lastnamefield;
	private JTextField streetfield;
	private JTextField cityfield;

	private Set<AbstractDialogListener> listeners;

	/**
	 * Instanziiert ein NewCustomerFrame
	 */
	public NewCustomerFrame() {

		super("New Customer");
		listeners = new HashSet<>();
		getContentPane().setLayout(new GridBagLayout());

		JLabel firstnamelabel = new JLabel("First Name:");
		GridBagConstraints gc = new GridBagConstraints();
		getContentPane().add(firstnamelabel, gc);

		firstnamefield = new JTextField();
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(firstnamefield, gc);

		JLabel lastnamelabel = new JLabel("Last Name:");
		gc = new GridBagConstraints();
		gc.gridx = 2;
		getContentPane().add(lastnamelabel, gc);

		lastnamefield = new JTextField();
		gc = new GridBagConstraints();
		gc.gridx = 3;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(lastnamefield, gc);

		JLabel streetlabel = new JLabel("Street:");
		gc = new GridBagConstraints();
		gc.gridy = 1;
		getContentPane().add(streetlabel, gc);

		streetfield = new JTextField();
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1.0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(streetfield, gc);

		JLabel citylabel = new JLabel("City:");
		gc = new GridBagConstraints();
		gc.gridy = 2;
		getContentPane().add(citylabel, gc);

		cityfield = new JTextField();
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1.0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(cityfield, gc);

		JButton cancelbutton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewCustomerFrame.this.setVisible(false);
				for (AbstractDialogListener<NewCustomerFrame> listener : listeners) {
					listener.dialogCanceled(NewCustomerFrame.this);
				}
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

			public void actionPerformed(ActionEvent e) {
				NewCustomerFrame.this.setVisible(false);
				for (AbstractDialogListener<NewCustomerFrame> listener : listeners) {
					listener.dialogOk(NewCustomerFrame.this);
				}
			}
		});
		
		addbutton.setText("Add Customer");
		gc = new GridBagConstraints();
		gc.gridx = 3;
		gc.gridy = 3;
		gc.weightx = 0.0;
		gc.anchor = GridBagConstraints.EAST;
		getContentPane().add(addbutton, gc);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Window close.");
				for (AbstractDialogListener<NewCustomerFrame> listener : listeners) {
					listener.dialogCanceled(NewCustomerFrame.this);
				}
			}
		});

	}

	/**
	 * Die Methode "getFirstName()" gibt den Inhalt des JTextField firstnamefield wieder.
	 * 
	 * @return Einen String mit dem Inhalt des Jtextfields "firstnamefield". Also der Vorname des Kunden.
	 */
	public String getFirstName() {
		return firstnamefield.getText();
	}

	/**
	 * Die Methode "getLastName()" gibt den Inhalt des JTextField lastnamefield wieder.
	 * 
	 * @return Einen String mit dem Inhalt des Jtextfields "lastnamefield". Also der Nachname des Kunden.
	 */	
	public String getLastName() {
		return lastnamefield.getText();
	}
	
	/**
	 * Die Methode "getStreet()" gibt den Inhalt des JTextField streetfield wieder.
	 * 
	 * @return Einen String mit dem Inhalt des Jtextfields "streetfield". Also die Anschrift des Kunden.
	 */
	public String getStreet() {
		return streetfield.getText();
	}
	
	/**
	 * Die Methode "getCity()" gibt den Inhalt des JTextField cityfield wieder.
	 * 
	 * @return Einen String mit dem Inhalt des Jtextfields "cityfield". Also der Wohnort des Kunden.
	 */
	public String getCity() {
		return cityfield.getText();
	}
	
	/**
	 * Hinzufügen eines DialogListener.
	 * 
	 * @param listener Ein AbstractDialogListener mit dem Generic eines NewCarFrame.
	 */
	public void addDialogListener(AbstractDialogListener<NewCustomerFrame> listener) {
		listeners.add(listener);
	}
	
	/**
	 * Entfernen eines DialogListener.
	 * 
	 * @param listener Ein AbstractDialogListener mit dem Generic eines NewCarFrame.
	 */
	public void removeDialogListener(AbstractDialogListener<NewCustomerFrame> listener) {
		listeners.remove(listener);
	}
}
