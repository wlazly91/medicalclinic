package medicalclinic.db;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Klasa reprezentuj¹ca poradnie w przychodni
 * */
@Entity
@Table(name = "CLINICS")
public class Clinics implements ObjectDB
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 830316204149596619L;

	@Id
	@Column (name = "ID_CLINICS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDCLINICS") 
	@SequenceGenerator(name="IDCLINICS", sequenceName = "IDCLINICS", allocationSize=1)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy="clinic")
	private Set<DoctorOfficeHours> doctorHours;

	
	public Clinics() {}

	public Clinics(int idC, String name, Set<DoctorOfficeHours> dictorHours) {
		this.name = name;
	}
	
	
	public void setDictorHours(Set<DoctorOfficeHours> dictorHours) {
		this.doctorHours = dictorHours;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<DoctorOfficeHours> getDoctorHours() {
		return doctorHours;
	}
}
