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
 * Klasa reprezentuj¹ca tabelê History Diseases
 * (historia choroby)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "HISTORY_DISEASES")
public class HistoryDiseases implements ObjectDB {


	private static final long serialVersionUID = -5161160493512899651L;

	@Id
	@Column(name = "ID_HISTORY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDHISTORYDISEASES") 
	@SequenceGenerator(name="IDHISTORYDISEASES", sequenceName = "IDHISTORYDISEASES", allocationSize=1)
	private int idHistory;
	
	@Column(name = "ID_PATIENT")
	private int idPatient;
	
	@Column(name = "ID_DISEASES")
	private int idDiseases;
	
	@Column(name = "ID_VISITS")
	private int idVisit;
	
	/**
	 * @param idDiseases the idDiseases to set
	 */
	public void setIdDiseases(int idDiseases) {
		this.idDiseases = idDiseases;
	}
	
	/**
	 * @param idHistory the idHistory to set
	 */
	public void setIdHistory(int idHistory) {
		this.idHistory = idHistory;
	}
	
	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}
	
	/**
	 * @param idVisit the idVisit to set
	 */
	public void setIdVisit(int idVisit) {
		this.idVisit = idVisit;
	}
	
	/**
	 * @return the idDiseases
	 */
	public int getIdDiseases() {
		return idDiseases;
	}
	
	/**
	 * @return the idHistory
	 */
	public int getIdHistory() {
		return idHistory;
	}
	
	/**
	 * @return the idPatient
	 */
	public int getIdPatient() {
		return idPatient;
	}
	
	/**
	 * @return the idVisit
	 */
	public int getIdVisit() {
		return idVisit;
	}
	
}
