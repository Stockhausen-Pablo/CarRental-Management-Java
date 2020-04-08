package calendarmanagement;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.github.lgooddatepicker.optionalusertools.DateHighlightPolicy;
import com.github.lgooddatepicker.zinternaltools.HighlightInformation;

import contractmanagement.Contract;
import contractmanagement.IContractDataStore;

/**
 * Die Klasse "CarHighlightPolicy implementiert das Interface DateHighlightPolicy.
 * Wir erstellen uns unsere eigene zugeschnitten Policy für die Highlight-Evaluierung der Kalendertage.
 * 
 * @author Pablo Stockhausen
 *
 */
public class CarHighlightPolicy implements DateHighlightPolicy{
	
	/** Schnittstelle zu der Auftragsdatenbank */
	private IContractDataStore contractDatastore;
	
	/** Das im ListCarPanel ausgewählte Auto*/
	private int selectedcar;
	
	/**
	 * Instanziiert eine neue CarHighlightPolicy.
	 *
	 * @param contractdatastore Schnittstelle zur Auftragsdatenbank
	 * @param selectedcar das im ListCarPanel ausgewählte Auto
	 */
	public CarHighlightPolicy(IContractDataStore contractdatastore, int selectedcar) {
		this.contractDatastore = contractdatastore;
		this.selectedcar = selectedcar;
	}
	
	/**
	 * Holt sich die spezifischen Highlight Informationen oder gibt null aus.
	 *
	 * @param date die dates aus dem CalendarFrame
	 * @return highlight information oder null
	 */
	@Override
	public HighlightInformation getHighlightInformationOrNull(LocalDate date) {

	/*----------------------------------------------------------------------*/
	/*importantlist = filtert alle contracts nach der ausgeweahlten carid*/
	try {
		Collection<Contract> importantlist = contractDatastore.getAllContracts();
		for(Iterator<Contract> it = importantlist.iterator(); it.hasNext();) {
			if(!it.next().getCarid().contains(""+selectedcar)){
				
				it.remove();
			}
		}
		ArrayList<Contract> newList = new ArrayList<Contract>(importantlist);
		//Nun gehen wir die neue Liste durch und markieren die Zeiträume
		for (int i = 0; i < newList.size(); i++) {
			if(date.isEqual(newList.get(i).getFrom())) {
				return new HighlightInformation(Color.green);
			}
			if(date.isEqual(newList.get(i).getUntil())) {
				return new HighlightInformation(Color.red);
			}
			
			if(date.isAfter(newList.get(i).getFrom()) && date.isBefore(newList.get(i).getUntil())) {
				return new HighlightInformation(Color.orange);
			}
			
		}
		
	} catch (IndexOutOfBoundsException e) {
		System.out.println("error");
	}
        return null;
	}
	


}
