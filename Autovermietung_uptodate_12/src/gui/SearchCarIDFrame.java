package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * Die Klasse "SearCarIDFrame" erweitert die Klasse JFrame und bietet eine grafische Oberfläche zum 
 * Verknüpfen der Auto-ID zum jeweiligen Fahrzeug, sodass dieses in der Tabelle hervorgehoben wird.
 * @author Gruppe12
 *
 */
public class SearchCarIDFrame extends JFrame {

	/** Das caridfield. */
	private JTextField caridfield;
	
	/** Der Boolean-Wert cancelled */
	private boolean cancelled;
	
	/**
	 * Instanziiert ein neues Such-Auto-IDFenster.
	 */
	public SearchCarIDFrame() {
		
		super("Enter a Car ID: ");
		setLayout(new GridBagLayout());
		
		caridfield = new JTextField();
		caridfield.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
	                e.consume();
	            }
	        }
		});
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.weightx = 2.0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(caridfield, gc);

		JButton addbutton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelled = false;
				dispose();
			}
		});
		addbutton.setText("Search Car");
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 0.0;
		gc.anchor = GridBagConstraints.EAST;
		getContentPane().add(addbutton, gc);

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
	 * Indikator ob der Suchprozess abgebrochen wurde oder nicht.
	 * 
	 * @return true oder false, je nachdem.
	 */
	public boolean isCancelled() {
		return cancelled;
	}
	
}
