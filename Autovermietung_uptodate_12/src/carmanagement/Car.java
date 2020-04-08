package carmanagement;

import java.util.UUID;

/**
 * Die Klasse "Car" ist unser Kundenelement, dass über verschiedene Attribute verfügt.
 * 
 * @author Gruppe12
 *
 */
public class Car {	
    
    /** Das Fahrzeugmodell */
    private String carModel;
    
    /** Die Anzahl an Sitzen */
    private String anzSitze;
    
    /** Der verwendete Kraftstoff */
    private String kraftstoff;
	
	/** Der Preis pro Tag */
	private int carprice;
	
    /** Die ID */
    private String id;
    
	/**
	 * Instanziiert eine neues Auto.
	 */
	public Car() {
		id = UUID.randomUUID().toString();
	}
    
    /**
     * Instanziiert eine neues Auto.
     *
     * @param carModel Das Fahrzeugmodell
     * @param anzSitze Die Anzahl an Sitzen
     * @param kraftstoff Der verwendete Kraftstoff
     * @param carprice Der Preis pro Tag
     */
    public Car(String carModel, String anzSitze, String kraftstoff, int carprice) {
    	this();
    	this.carModel = carModel;
    	this.anzSitze = anzSitze;
    	this.kraftstoff = kraftstoff;
    	this.carprice = carprice;
    }

	/**
	 * Holt sich das Fahrzeugmodell
	 *
	 * @return Fahrzeugmodell
	 */
	public String getCarModel() {
		return carModel;
	}

	/**
	 * Belegt das Fahrzeugmodell
	 *
	 * @param carModel neues Fahrzeugmodell
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	/**
	 * Holt sich den Fahrzeugpreis
	 *
	 * @return Fahrzeugpreis
	 */
	public int getCarprice() {
		return carprice;
	}

	/**
	 * Belegt den Fahrzeugpreis
	 *
	 * @param carprice der neue Fahrzeugpreis
	 */
	public void setCarprice(int carprice) {
		this.carprice = carprice;
	}

	/**
	 * Holt sich die ID
	 *
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Belegt die ID
	 *
	 * @param id die neue ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
    /**
     * Holt sich die Anzahl an Sitzen
     *
     * @return Anzahl an Sitzen
     */
    public String getAnzSitze() {
		return anzSitze;
	}
	
	/**
	 * Belegt die Anzahl an Sitzen
	 *
	 * @param anzSitze neue Anzahl an Sitzen
	 */
	public void setAnzSitze(String anzSitze) {
		this.anzSitze = anzSitze;
	}
	
	/**
	 * Holt sich den verwendeten Kraftstoff
	 *
	 * @return Kraftstoff
	 */
	public String getKraftstoff() {
		return kraftstoff;
	}
	
	/**
	 * Belegt den verwendeten Krafstoff
	 *
	 * @param kraftstoff neue Kraftstoff
	 */
	public void setKraftstoff(String kraftstoff) {
		this.kraftstoff = kraftstoff;
	}

	
	/**
	 * Aus der ID berechneter HashCode.
	 *
	 * @return Hashwert als int
	 */
	public int hashCode() {
		return id.hashCode();
	}
	
	/**
	 * Boolean-Abfrage ob ein Objekte einem anderen gleich ist.
	 * Aufgrundlage seiner ID.
	 *
	 * @param obj Ein Objekt der Klasse "Car"
	 * @return true, wenn erfolgreich
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Car) {
			Car otherCar = (Car) obj;
			return id.equals(otherCar.getId());
		}
		return false;
	}
}
