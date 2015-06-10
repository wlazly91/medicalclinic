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
 * klasa reprezentuj�ca tabel� SCHEDULE_VISITS
 * (harmonogram wizyt)
 * @author �ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "SCHEDULE_VISITS")
public class ScheduleVisits implements ObjectDB {

	private static final long serialVersionUID = 7732972942371357676L;

	@Id
	@Column(name = "ID_SV")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDSCHULEVISITS") 
	@SequenceGenerator(name="IDSCHULEVISITS", sequenceName = "IDSCHULEVISITS", allocationSize=1)
	private int idSV;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCTOR")
	private Doctor idDoctor;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLINICS")
	private Clinics idClinics;
	
	@ManyToOne
	@JoinColumn(name = "ID_PATIENT")
	private Patient idPatient;
	
	@Column(name = "DATE_SV")
	private Date dateSV;
	
	@Column(name = "HOURS_SV")
	private String hoursSV;
	
	
	public ScheduleVisits() {}
	
	public ScheduleVisits(Date dateS, String hours, Doctor doc, Clinics clin, Patient pat) {
		this.dateSV = dateS;
		this.hoursSV = hours;
		this.idClinics = clin;
		this.idDoctor = doc;
		this.idPatient = pat;
	}
	/**
	 * @param dateSV the dateSV to set
	 */
	public void setDateSV(Date dateSV) {
		this.dateSV = dateSV;
	}
	
	/**
	 * @param hoursSV the hoursSV to set
	 */
	public void setHoursSV(String hoursSV) {
		this.hoursSV = hoursSV;
	}
	
	/**
	 * @param idClinics the idClinics to set
	 */
	public void setIdClinics(Clinics idClinics) {
		this.idClinics = idClinics;
	}
	
	/**
	 * @param idDoctor the idDoctor to set
	 */
	public void setIdDoctor(Doctor idDoctor) {
		this.idDoctor = idDoctor;
	}
	
	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(Patient idPatient) {
		this.idPatient = idPatient;
	}
	
	/**
	 * @param idSV the idSV to set
	 */
	public void setIdSV(int idSV) {
		this.idSV = idSV;
	}
	
	/**
	 * @return the dateSV
	 */
	public Date getDateSV() {
		return dateSV;
	}
	
	/**
	 * @return the hoursSV
	 */
	public String getHoursSV() {
		return hoursSV;
	}
	
	/**
	 * @return the idClinics
	 */
	public Clinics getIdClinics() {
		return idClinics;
	}
	
	/**
	 * @return the idDoctor
	 */
	public Doctor getIdDoctor() {
		return idDoctor;
	}
	
	/**
	 * @return the idPatient
	 */
	public Patient getIdPatient() {
		return idPatient;
	}
	
	/**
	 * @return the idSV
	 */
	public int getIdSV() {
		return idSV;
	}
	
}
