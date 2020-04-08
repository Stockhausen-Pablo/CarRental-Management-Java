package carmanagement;

import java.util.Collection;

/**
 * Das Interface "ICarDataStore" dient als Schnittstelle für die Autodatenbank und liefert uns verschiedene Funktionen.
 * 
 * @author Gruppe12
 *
 */
public interface ICarDataStore {

	/**
	 * Fügt einen neues Auto hinzu.
	 *
	 * @param car neues Auto
	 */
	public void addCar(Car car);

	/**
	 * Entfernt ein Auto.
	 *
	 * @param car Ein Objekt der Klasse "Car", dass entfernt werden soll
	 */
	public void removeCar(Car car);

	/**
	 * Holt sich ein Auto auf Basis seiner ID.
	 *
	 * @param id die ID des Autos
	 * @return Das zu der ID passenden Fahrzeug
	 */
	public Car getCar(String id);

	/**
	 * Holt sich das Auto auf Basis seiner Position in der Liste (index)
	 *
	 * @param index der gefragte Index
	 * @return das Auto, an der Position des Index
	 */
	public Car getCar(int index);

	/**
	 * Gibt die gesamte Anzahl an Fahrzeugen aus.
	 *
	 * @return Anzahl als integerwert
	 */
	public int getCarCount();

	/**
	 * Gibt alle Fahrzeuge als eine ArrayList aus.
	 *
	 * @return ArrayList mit allen Fahrzeugen
	 */
	public Collection<Car> getAllCars();

	/**
	 * Fügt einen CarListener hinzu.
	 *
	 * @param listener Objekt der Schnittstelle zu dem ICarListener
	 */
	public void addCarListener(ICarListener listener);

	/**
	 * Entfernt einen CarListener.
	 *
	 * @param listener Objekt der Schnittstelle zu dem ICarListener
	 */
	public void removeCarListener(ICarListener listener);
}
