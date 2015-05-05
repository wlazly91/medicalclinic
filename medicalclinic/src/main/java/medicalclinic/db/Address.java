package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Klasa reprezentuj¹ca strukturê tabeli ADDRESS
 * reprezentuj¹ca Adres 
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "ADDRESS") 
public class Address implements ObjectDB
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8852616135488393442L;

	@Id
	@Column(name = "ID_ADDRESS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDADDRESS") 
	@SequenceGenerator(name="IDADDRESS", sequenceName = "IDADDRESS", allocationSize=1)
	private int id;
	
	@Column(name = "ID_LOCALITY")
	private int id_locality;
	
	@Column(name = "ID_POSTOFFICE")
	private int id_postoffice;
	
	@Column(name = "ID_STREETS")
	private int id_strets;
	
	@Column(name = "ID_COUNTRIES")
	private int id_countries;
	
	@Column(name = "HOUSE_NUMBER")
	private int house_number;
	
	/**
	 * @param house_number the house_number to set
	 */
	public void setHouse_number(int house_number) {
		this.house_number = house_number;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	 
	/**
	 * @param id_countries the id_countries to set
	 */
	public void setId_countries(int id_countries) {
		this.id_countries = id_countries;
	}
	
	/**
	 * @param id_locality the id_locality to set
	 */
	public void setId_locality(int id_locality) {
		this.id_locality = id_locality;
	}
	
	/**
	 * @param id_postoffice the id_postoffice to set
	 */
	public void setId_postoffice(int id_postoffice) {
		this.id_postoffice = id_postoffice;
	}
	
	/**
	 * @param id_strets the id_strets to set
	 */
	public void setId_strets(int id_strets) {
		this.id_strets = id_strets;
	}
	
	/**
	 * @return the house_number
	 */
	public int getHouse_number() {
		return house_number;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the id_countries
	 */
	public int getId_countries() {
		return id_countries;
	}
	
	/**
	 * @return the id_locality
	 */
	public int getId_locality() {
		return id_locality;
	}
	
	/**
	 * @return the id_postoffice
	 */
	public int getId_postoffice() {
		return id_postoffice;
	}
	
	/**
	 * @return the id_strets
	 */
	public int getId_strets() {
		return id_strets;
	}
}
