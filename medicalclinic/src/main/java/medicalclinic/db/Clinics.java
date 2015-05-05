package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
