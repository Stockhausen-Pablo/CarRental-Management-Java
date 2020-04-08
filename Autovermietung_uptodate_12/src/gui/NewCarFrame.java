package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
 * Die Klasse "NewCarFrame" erweitert die Klasse JFrame und bietet eine grafische Oberfläche zur 
 * Erstellung eines neuen Fahrzeuges. Außerdem kann sie bestimmte Listener benachrichtigen.
 * @author Gruppe12
 *
 */
public class NewCarFrame extends JFrame {

	private JTextField carmodel;
	private JTextField anzSitz;
	private JTextField kraftstoff;
	private JTextField price;

	private Set<AbstractDialogListener> listeners;
	
	/**
	 * Instanziiert ein NewCarFrame
	 */
	public NewCarFrame() {

		super("New Car");
		listeners = new HashSet<>();
		getContentPane().setLayout(new GridBagLayout());

		JLabel modellabel = new JLabel("Model:");
		GridBagConstraints gc = new GridBagConstraints();
		getContentPane().add(modellabel, gc);

		carmodel = new JTextField();
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(carmodel, gc);

		JLabel anzSitzlabel = new JLabel("Seats:");
		gc = new GridBagConstraints();
		gc.gridx = 2;
		getContentPane().add(anzSitzlabel, gc);

		anzSitz = new JTextField();
		//KeyListener, der nur numerische Werte als Eingabe erlaubt
		anzSitz.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
	                e.consume();
	            }
	        }
		});
		gc = new GridBagConstraints();
		gc.gridx = 3;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(anzSitz, gc);

		JLabel fuellabel = new JLabel("Fuel:");
		gc = new GridBagConstraints();
		gc.gridy = 1;
		getContentPane().add(fuellabel, gc);

		kraftstoff = new JTextField();
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1.0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(kraftstoff, gc);

		JLabel pricelabel = new JLabel("Price:");
		gc = new GridBagConstraints();
		gc.gridy = 2;
		getContentPane().add(pricelabel, gc);
		
		price = new JTextField();
		
		//KeyListener, der nur numerische Werte als Eingabe erlaubt
		price.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
	                e.consume();
	            }
	        }
		});
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1.0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(price, gc);

		JButton cancelbutton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewCarFrame.this.setVisible(false);
				for (AbstractDialogListener<NewCarFrame> listener : listeners) {
					listener.dialogCanceled(NewCarFrame.this);
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
				NewCarFrame.this.setVisible(false);
				for (AbstractDialogListener<NewCarFrame> listener : listeners) {
					listener.dialogOk(NewCarFrame.this);
				}
			}
		});
		addbutton.setText("Add Car");
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
				for (AbstractDialogListener<NewCarFrame> listener : listeners) {
					listener.dialogCanceled(NewCarFrame.this);
				}
			}
		});
	}
	
	/**
	 * Die Methode "getCarmodel()" gibt den Inhalt des JTextField carmodel wieder.
	 * 
	 * @return Einen String mit dem Inhalt des Jtextfields "carmodel".
	 */
	public String getCarmodel() {
		return carmodel.getText();
	}
	
	/**
	 * Die Methode "getAnzSitz()" gibt den Inhalt des JTextField anzSitz wieder.
	 * 
	 * @return Einen String mit dem Inhalt des Jtextfields "anzSitz".
	 */
	public String getAnzSitz() {
		return anzSitz.getText();
	}

	/**
	 * Die Methode "getKraftstoff()" gibt den Inhalt des JTextField kraftstoff wieder.
	 * 
	 * @return Einen String mit dem Inhalt des Jtextfields "kraftstoff".
	 */
	public String getKraftstoff() {
		return kraftstoff.getText();
	}

	/**
	 * Die Methode "getPrice()" gibt den Inhalt des JTextField price wieder. Da es sich um ein Textfield handelt, müssen
	 * wir zunächst über ein parseInt den Text zu einem int wert umwandeln. Da wir dem price textfield ein KeyListener
	 * hinzugefügt haben, können wir uns sicher sein, dass es sich um einen nummerischen Wert handelt.
	 * 
	 * @return Die Rückgabe ist der Inhalt des Jtextfields "price" als int, für die spätere Preiskalkulation. 
	 */
	public int getPrice() {
		if (price.getText().equals("")) {
			return 0;
		} else {
			int jml = Integer.parseInt(price.getText());
			return jml;
		} 
	}

	/**
	 * Hinzufügen eines DialogListener.
	 * 
	 * @param listener Ein AbstractDialogListener mit dem Generic eines NewCarFrame.
	 */
	public void addDialogListener(AbstractDialogListener<NewCarFrame> listener) {
		listeners.add(listener);
	}
	
	/**
	 * Entfernen eines DialogListener.
	 * 
	 * @param listener Ein AbstractDialogListener mit dem Generic eines NewCarFrame.
	 */
	public void removeDialogListener(AbstractDialogListener<NewCarFrame> listener) {
		listeners.remove(listener);
	}

}
