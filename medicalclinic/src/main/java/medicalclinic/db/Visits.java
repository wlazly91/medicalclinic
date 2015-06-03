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
    @JoinColumn(name="ID_PATIENT")
    private Patient patient;
	
	@Column(name = "DATE_VISITS")
	private Date dateVisit;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCTOR")
	private Doctor doctor;
	
	
	public Visits() {}
	
	public Visits(int id, Patient pat, Doctor doc, Date data) {
		this.dateVisit = data;
		this.doctor = doc;
		this.idVisits = id;
		this.patient = pat;
	}

	public void setDateVisit(Date dateVisit) {
		this.dateVisit = dateVisit;
	}

	public void setIdDoc(Doctor idDoc) {
		this.doctor = idDoc;
	}
	
	public void setPat(Patient pat) {
		this.patient = pat;
	}

	public void setIdVisits(int idVisits) {
		this.idVisits = idVisits;
	}
	
	public Patient getPat() {
		return patient;
	}

	public Date getDateVisit() {
		return dateVisit;
	}

	public Doctor getIdDoc() {
		return doctor;
	}

	public int getIdVisits() {
		return idVisits;
	}
	
}
