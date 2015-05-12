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
 * Klasa reprezentuj�ca tabel� bazy danych Docotr Office Hours
 * (terminy przyj�� lekarzy)
 * @author �ukasz Kochanek
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
	private int idDoc;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLINICS")
	private Clinics idClinics;
	
	@Column(name = "HOURS_FROM")
	private String hoursFrom;
	
	@Column(name = "HOURS_TO")
	private String hoursTo;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCTOR")
	private Doctor idDoctor;
	
	
	public DoctorOfficeHours() {}
	
	
	public DoctorOfficeHours(String from, String to) {
		this.hoursFrom = from;
		this.hoursTo = to;
	}
	/**
	 * @param hoursFrom the hoursFrom to set
	 */
	public void setHoursFrom(String hoursFrom) {
		this.hoursFrom = hoursFrom;
	}
	/**
	 * @param hoursTo the hoursTo to set
	 */
	public void setHoursTo(String hoursTo) {
		this.hoursTo = hoursTo;
	}
	
	/**
	 * @param idClinics the idClinics to set
	 */
	public void setIdClinics(Clinics idClinics) {
		this.idClinics = idClinics;
	}
	
	/**
	 * @param idDoc the idDoc to set
	 */
	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}
	
	/**
	 * @param idDoctor the idDoctor to set
	 */
	public void setIdDoctor(Doctor idDoctor) {
		this.idDoctor = idDoctor;
	}
	
	/**
	 * @return the hoursFrom
	 */
	public String getHoursFrom() {
		return hoursFrom;
	}
	
	/**
	 * @return the hoursTo
	 */
	public String getHoursTo() {
		return hoursTo;
	}
	
	/**
	 * @return the idClinics
	 */
	public Clinics getIdClinics() {
		return idClinics;
	}
	
	/**
	 * @return the idDoc
	 */
	public int getIdDoc() {
		return idDoc;
	}
	
	/**
	 * @return the idDoctor
	 */
	public Doctor getIdDoctor() {
		return idDoctor;
	}
	
}
