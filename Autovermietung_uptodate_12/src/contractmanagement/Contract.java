package contractmanagement;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Die Klasse "Contract" ist unser Auftragselement, dass über verschiedene Attribute verfügt.
 * @author Gruppe12
 *
 */
public class Contract {
	
	/** Startdatum */
	private LocalDate from;
	
	/** Enddatum */
	private LocalDate until;
	
	/** KundenID */
	private String customerid;
	
	/** FahrzeugID. */
	private String carid;
	
	/** der finale Preis */
	private int endprice;
	
	/** die AuftragsID */
	private String contractid;

	/**
	 * Instanziiert einen neuen Auftrag.
	 */
	public Contract() {
		contractid = UUID.randomUUID().toString();
	}

	/**
	 * Instanziiert einen neuen Auftrag.
	 *
	 * @param from das Anfangsdatum
	 * @param until das Enddatum
	 * @param customerid die KundenID
	 * @param carid die FahrzeugID
	 * @param endprice der finale Preis
	 */
	public Contract(LocalDate from, LocalDate until, String customerid, String carid, int endprice) {
		this();
		this.from = from;
		this.until = until;
		this.customerid = customerid;
		this.carid = carid;
		this.endprice = endprice;
	}

	/**
	 * Holt sich das Startdatum.
	 *
	 * @return Startdatum
	 */
	public LocalDate getFrom() {
		return from;
	}

	/**
	 * Belegt das Startdatum
	 *
	 * @param from neues Startdatum
	 */
	public void setFrom(LocalDate from) {
		this.from = from;
	}

	/**
	 * Holt sich das Enddatum.
	 *
	 * @return Enddatum
	 */
	public LocalDate getUntil() {
		return until;
	}

	/**
	 * Belegt das Enddatum.
	 *
	 * @param until das neue Enddatum
	 */
	public void setUntil(LocalDate until) {
		this.until = until;
	}

	/**
	 * Holt sich die KundenID
	 *
	 * @return KundenID
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * Belegt die KundenID
	 *
	 * @param customerid neue KundenID
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	/**
	 * Holt sich die FahrzeugID
	 *
	 * @return FahrzeugID
	 */
	public String getCarid() {
		return carid;
	}

	/**
	 * Belegt die FahrzeugID
	 *
	 * @param carid neue FahrzeugID
	 */
	public void setCarid(String carid) {
		this.carid = carid;
	}

	/**
	 * Holt sich den finalen Preis
	 *
	 * @return finaler Preis
	 */
	public int getEndprice() {
		return endprice;
	}

	/**
	 * Belegt den finalen Preis.
	 *
	 * @param endprice neuer finaler Preis
	 */
	public void setEndprice(int endprice) {
		this.endprice = endprice;
	}

	/**
	 * Holt sich die AuftragsID
	 *
	 * @return AuftragsID
	 */
	public String getContractid() {
		return contractid;
	}

	/**
	 * Belegt die AuftragsID
	 *
	 * @param contractid neue AuftragsID
	 */
	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
}
