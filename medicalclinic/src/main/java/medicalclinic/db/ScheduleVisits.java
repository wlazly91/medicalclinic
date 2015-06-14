/**
 * 
 */
package medicalclinic.db;

import java.sql.Time;
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
	private Time hoursSV;

	
	
	public ScheduleVisits() {}
	
	public ScheduleVisits(Date dateS, Time hours, Doctor doc, Clinics clin, Patient pat) {
		this.dateSV = dateS;
		this.hoursSV = hours;
		this.idClinics = clin;
		this.idDoctor = doc;
		this.idPatient = pat;
	}
	
	public void setDateSV(Date dateSV) {
		this.dateSV = dateSV;
	}
	
	public void setHoursSV(Time hoursSV) {
		this.hoursSV = hoursSV;
	}
	
	public void setIdClinics(Clinics idClinics) {
		this.idClinics = idClinics;
	}
	
	public void setIdDoctor(Doctor idDoctor) {
		this.idDoctor = idDoctor;
	}
	
	public void setIdPatient(Patient idPatient) {
		this.idPatient = idPatient;
	}
	
	public void setIdSV(int idSV) {
		this.idSV = idSV;
	}
	
	public Date getDateSV() {
		return dateSV;
	}
	
	public Time getHoursSV() {
		return hoursSV;
	}
	
	public Clinics getIdClinics() {
		return idClinics;
	}
	
	public Doctor getIdDoctor() {
		return idDoctor;
	}
	
	public Patient getIdPatient() {
		return idPatient;
	}
	
	public int getIdSV() {
		return idSV;
	}
}
