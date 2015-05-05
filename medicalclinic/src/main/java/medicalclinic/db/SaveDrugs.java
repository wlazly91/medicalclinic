/**
 * 
 */
package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Klasa reprezentuj¹ca tabelê Save_drugs
 * (przepisane leki)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "SAVE_DRUGS")
public class SaveDrugs implements ObjectDB{

	private static final long serialVersionUID = 2064783250123161663L;

	@Id
	@Column(name = "ID_SD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDSAVEDRUGS") 
	@SequenceGenerator(name="IDSAVEDRUGS", sequenceName = "IDSAVEDRUGS", allocationSize=1)
	private int idSD;
	
	@Column(name = "ID_VISITS")
	private int idVisits;
	
	@Column(name = "ID_DRUGS")
	private int idDrugs;
	
	@Column(name = "ID_PATIENT")
	private int idPatient;
	
	/**
	 * @param idDrugs the idDrugs to set
	 */
	public void setIdDrugs(int idDrugs) {
		this.idDrugs = idDrugs;
	}
	
	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}
	
	/**
	 * @param idSD the idSD to set
	 */
	public void setIdSD(int idSD) {
		this.idSD = idSD;
	}
	
	/**
	 * @param idVisits the idVisits to set
	 */
	public void setIdVisits(int idVisits) {
		this.idVisits = idVisits;
	}
	
	/**
	 * @return the idDrugs
	 */
	public int getIdDrugs() {
		return idDrugs;
	}
	
	/**
	 * @return the idPatient
	 */
	public int getIdPatient() {
		return idPatient;
	}
	
	/**
	 * @return the idSD
	 */
	public int getIdSD() {
		return idSD;
	}
	
	/**
	 * @return the idVisits
	 */
	public int getIdVisits() {
		return idVisits;
	}
}
