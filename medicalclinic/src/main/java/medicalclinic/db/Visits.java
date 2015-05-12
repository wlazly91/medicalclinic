/**
 * 
 */
package medicalclinic.db;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * klasa reprezentuj¹ca tabelê VISITS
 * wizyty
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "VISITS")
public class Visits implements ObjectDB {

	private static final long serialVersionUID = 3172171869566754775L;
	
	@Id
	@Column(name = "ID_VISITS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDVISITS") 
	@SequenceGenerator(name="IDVISITS", sequenceName = "IDVISITS", allocationSize=1)
	private int idVisits;
	
	@ManyToOne
	@JoinColumn(name = "ID_DISEASES")
	private Diseases idDis;
	
	@ManyToOne
	@JoinColumn(name = "ID_PATIENT")
	private Patient idPatient;
	
	@Column(name = "DATE_VISITS")
	private Date dateVisit;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCTOR")
	private Doctor idDoc;
	
	
	/**
	 * @param dateVisit the dateVisit to set
	 */
	public void setDateVisit(Date dateVisit) {
		this.dateVisit = dateVisit;
	}
	
	/**
	 * @param idDis the idDis to set
	 */
	public void setIdDis(Diseases idDis) {
		this.idDis = idDis;
	}
	
	/**
	 * @param idDoc the idDoc to set
	 */
	public void setIdDoc(Doctor idDoc) {
		this.idDoc = idDoc;
	}
	
	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(Patient idPatient) {
		this.idPatient = idPatient;
	}
	
	/**
	 * @param idVisits the idVisits to set
	 */
	public void setIdVisits(int idVisits) {
		this.idVisits = idVisits;
	}
	
	/**
	 * @return the dateVisit
	 */
	public Date getDateVisit() {
		return dateVisit;
	}
	
	/**
	 * @return the idDis
	 */
	public Diseases getIdDis() {
		return idDis;
	}
	
	/**
	 * @return the idDoc
	 */
	public Doctor getIdDoc() {
		return idDoc;
	}
	
	/**
	 * @return the idPatient
	 */
	public Patient getIdPatient() {
		return idPatient;
	}
	
	/**
	 * @return the idVisits
	 */
	public int getIdVisits() {
		return idVisits;
	}
	
}
