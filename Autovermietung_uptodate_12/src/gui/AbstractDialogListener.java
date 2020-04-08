package gui;
/**
 * Die Abstrakte Klasse "AbstractDialogListener" ist unserer Listener, den wir in den einzelnen Panels zur
 * Abfrage der Varianten, wie ein "New Object"-Fenster geschlossen werden kann, nutzen.
 * @author Pablo Stockhausen
 * @version 1.0
 * @param <T> Ein Generic, dass uns erm�glicht das wir unterschiedliche Arten von Frames der Klasse �berreichen k�nnen.
 */
public abstract class AbstractDialogListener<T> {
	
	/**
	 * Bietet die M�glichkeit zu identifizieren, ob ein Erstellungsprozess-Fenster mit "okay" beendet wurde.
	 * Die Methode ist abstract, da wir unterschiedliche Inhalte f�r die jeweiligen Frames ausf�hren m�ssen.
	 * @param dialog Eingabe des Dialog-Frames
	 */
	public abstract void dialogOk(T dialog);
	
	/**
	 * Bietet die M�glichkeit zu identifizieren, ob ein Erstellungsprozess-Fenster mit "abbrechen" beendet wurde.
	 * Man kann hier die Methode schon deklarieren, da sie keine Funktionalit�ten beinhaltet.
	 * @param dialog Eingabe des Dialog-Frames
	 */
	public void dialogCanceled(T dialog) {
		
	}
}
