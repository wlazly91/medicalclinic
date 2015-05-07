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
 * Klasa reprezentuj¹ca taelê locality
 * (miejscowoœci )
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "LOCALITY")
public class Locality implements ObjectDB {

	private static final long serialVersionUID = 8315241961443729009L;
	
	@Id
	@Column(name = "ID_LOCALITY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDLOCALITY") 
	@SequenceGenerator(name="IDLOCALITY", sequenceName = "IDLOCALITY", allocationSize=1)
	private int idLocality;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "COUNTRY")
	private int country;
	
	@Column(name = "VILLAGE")
	private int village;
	
	
	/**
	 * 
	 */
	public Locality() {}
	
	/**
	 * 
	 * */
	public Locality(String name, int country, int village)
	{
		this.country =country;
		this.name = name;
		this.village = village;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(int country) {
		this.country = country;
	}
	
	/**
	 * @param idLocality the idLocality to set
	 */
	public void setIdLocality(int idLocality) {
		this.idLocality = idLocality;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param village the village to set
	 */
	public void setVillage(int village) {
		this.village = village;
	}
	
	/**
	 * @return the country
	 */
	public int getCountry() {
		return country;
	}
	
	/**
	 * @return the idLocality
	 */
	public int getIdLocality() {
		return idLocality;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the village
	 */
	public int getVillage() {
		return village;
	}
}
