package carmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Die Klasse "CarDataStore" implementiert den "ICarDataStore" und dient als
 * Fahrzeugverwaltungsdatenbank mit verschiedenen funktionalen Eigenschaften.
 * 
 * @author Gruppe12
 *
 */
public class CarDataStore implements ICarDataStore {

	/** Die Fahrzeuge als Map */
	private Map<String, Car> cars;
	
	/** Die Fahrzeuge als Liste */
	private List<Car> carlist;

	/** Die Listeners */
	private Set<ICarListener> listeners;

	/**
	 * Instanziiert einen neuen CarDataStore
	 */
	public CarDataStore() {
		cars = new HashMap<>();
		carlist = new ArrayList<>();
		listeners = new HashSet<>();
	}

	/**
	 * Fügt einen neues Auto hinzu.
	 *
	 * @param car neues Auto
	 */
	@Override
	public void addCar(Car car) {
		cars.put(car.getId(), car);
		carlist.add(car);

		for (ICarListener listener : listeners)
			listener.carsChanged();

	}

	/**
	 * Holt sich ein Auto auf Basis seiner ID.
	 *
	 * @param id die ID des Autos
	 * @return Das zu der ID passenden Fahrzeug
	 */
	@Override
	public Car getCar(String id) {
		cars.put(id, null);
		cars.containsKey(id);
		return cars.get(id);

	}

	/**
	 * Gibt alle Fahrzeuge als eine ArrayList aus.
	 *
	 * @return ArrayList mit allen Fahrzeugen
	 */
	@Override
	public Collection<Car> getAllCars() {

		return new ArrayList<>(cars.values());

	}

	/**
	 * Holt sich das Auto auf Basis seiner Position in der Liste (index)
	 *
	 * @param index der gefragte Index
	 * @return das Auto, an der Position des Index
	 */
	@Override
	public Car getCar(int index) {
		return carlist.get(index);
	}

	/**
	 * Fügt einen CarListener hinzu.
	 *
	 * @param listener Objekt der Schnittstelle zu dem ICarListener
	 */
	@Override
	public void addCarListener(ICarListener listener) {
		listeners.add(listener);
	}

	/**
	 * Entfernt einen CarListener.
	 *
	 * @param listener Objekt der Schnittstelle zu dem ICarListener
	 */
	@Override
	public void removeCarListener(ICarListener listener) {
		listeners.add(listener);
	}

	/**
	 * Gibt die gesamte Anzahl an Fahrzeugen aus.
	 *
	 * @return Anzahl als integerwert
	 */
	@Override
	public int getCarCount() {
		return cars.size();
	}

	/**
	 * Entfernt ein Auto.
	 *
	 * @param car Ein Objekt der Klasse "Car", dass entfernt werden soll
	 */
	@Override
	public void removeCar(Car car) {
		carlist.remove(car);
		cars.remove(car.getId(), car);
		for (ICarListener listener : listeners)
			listener.carsChanged();
	}

}
