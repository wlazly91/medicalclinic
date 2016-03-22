/**
 * 
 */
package medicalclinic.db;

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
 * Klasa reprezentuj¹ca tabelê bazy danych Docotr Office Hours
 * (terminy przyjêæ lekarzy)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "DOCTOR_OFFICE_HOURS")
public class DoctorOfficeHours implements ObjectDB {

	private static final long serialVersionUID = -7583092187360249349L;
	
	@Id
	@Column(name = "ID_DOC")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDDOCTOROFFICE") 
	@SequenceGenerator(name="IDDOCTOROFFICE", sequenceName = "IDDOCTOROFFICE", allocationSize=1)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLINICS")
	private Clinics clinic;
	
	@Column(name = "HOURS_FROM")
	private String hoursFrom;
	
	@Column(name = "HOURS_TO")
	private String hoursTo;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCTOR")
	private Doctor doc;
	
	
	public DoctorOfficeHours() {}
	
	
	public DoctorOfficeHours(int id, String from, String to, Doctor doc, Clinics clin) {
		this.hoursFrom = from;
		this.hoursTo = to;
		this.clinic = clin;
		this.doc = doc;
		this.id = id;
	}

	public void setHoursFrom(String hoursFrom) {
		this.hoursFrom = hoursFrom;
	}

	public void setHoursTo(String hoursTo) {
		this.hoursTo = hoursTo;
	}

	public void setIdClinics(Clinics idClinics) {
		this.clinic = idClinics;
	}

	public void setId(int idDoc) {
		this.id = idDoc;
	}

	public void setDoctor(Doctor idDoctor) {
		this.doc = idDoctor;
	}

	public String getHoursFrom() {
		return hoursFrom;
	}

	public String getHoursTo() {
		return hoursTo;
	}

	public Clinics getClinics() {
		return clinic;
	}

	public int getId() {
		return id;
	}

	public Doctor getDoctor() {
		return doc;
	}
	
}
