/**
 * 
 */
package medicalclinic.db;

import java.sql.Time;

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
 * Klasa reprezentująca tabelę bazy danych Docotr Office Hours
 * (terminy przyjęć  lekarzy)
 * @author Łukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "DOCTOR_OFFICE_HOURS")
public class DoctorOfficeHours implements ObjectDB {

	private static final long serialVersionUID = -7583092187360249349L;
	
	@Id
	@Column(name = "ID_DOCOFFICE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDDOCTOROFFICE") 
	@SequenceGenerator(name="IDDOCTOROFFICE", sequenceName = "IDDOCTOROFFICE", allocationSize=1)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLINICS")
	private Clinics clinic;
	
	@Column(name = "HOURS_FROM")
	private Time hoursFrom;
	
	@Column(name = "HOURS_TO")
	private Time hoursTo;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCTOR")
	private Doctor doc;
	
	@Column(name="VISITLENGHT")
	private int visitLenght;
	
	@Column(name="DAYOFWEEK")
	private String dayOfWeek;
	
	
	public DoctorOfficeHours() {}
	
	
	public DoctorOfficeHours(int id, Time from, Time to, Doctor doc, Clinics clin) {
		this.hoursFrom = from;
		this.hoursTo = to;
		this.clinic = clin;
		this.doc = doc;
		this.id = id;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public void setClinic(Clinics clinic) {
		this.clinic = clinic;
	}
	
	public void setDoc(Doctor doc) {
		this.doc = doc;
	}
	
	public void setVisitLenght(int visitLenght) {
		this.visitLenght = visitLenght;
	}
	
	public void setHoursFrom(Time hoursFrom) {
		this.hoursFrom = hoursFrom;
	}

	public void setHoursTo(Time hoursTo) {
		this.hoursTo = hoursTo;
	}

	public void setId(int idDoc) {
		this.id = idDoc;
	}

	public Clinics getClinic() {
		return clinic;
	}
	
	public Doctor getDoc() {
		return doc;
	}
	
	public Time getHoursFrom() {
		return hoursFrom;
	}
	
	public Time getHoursTo() {
		return hoursTo;
	}
	
	public int getId() {
		return id;
	}
	
	public int getVisitLenght() {
		return visitLenght;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	
}
