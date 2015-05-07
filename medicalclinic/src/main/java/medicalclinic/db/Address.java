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
 * Klasa reprezentuj¹ca strukturê tabeli ADDRESS
 * reprezentuj¹ca Adres 
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "ADDRESS") 
public class Address implements ObjectDB
{
	private static final long serialVersionUID = -8852616135488393442L;

	@Id
	@Column(name = "ID_ADDRESS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDADDRESS") 
	@SequenceGenerator(name="IDADDRESS", sequenceName = "IDADDRESS", allocationSize=1)
	private int id;
	
	@ManyToOne
    @JoinColumn(name = "ID_LOCALITY")
	private Locality idLocality;
	
	@ManyToOne
    @JoinColumn(name = "ID_POSTOFFICE")
	private PostOffice idPostoffice;
	
	@ManyToOne
    @JoinColumn(name = "ID_STREETS")
	private Streets idStrets;
	
	@ManyToOne
    @JoinColumn(name = "ID_COUNTIES")
	private Counties idCounties;
	
	@Column(name = "HOUSE_NUMBER")
	private int house_number;
	
	/**
	 * Default Constuctor
	 */
	public Address(){}
	
	
	/**
	 * Constuctor
	 * @param houseNumber variable int 
	 * */
	public Address(int houseNumber)
	{
		this.house_number = houseNumber;
	}
	
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
	 * @param idCounties the idCounties to set
	 */
	public void setIdCounties(Counties idCounties) {
		this.idCounties = idCounties;
	}
	
	/**
	 * @param idLocality the idLocality to set
	 */
	public void setIdLocality(Locality idLocality) {
		this.idLocality = idLocality;
	}
	
	/**
	 * @param idPostoffice the idPostoffice to set
	 */
	public void setIdPostoffice(PostOffice idPostoffice) {
		this.idPostoffice = idPostoffice;
	}
	
	/**
	 * @param idStrets the idStrets to set
	 */
	public void setIdStrets(Streets idStrets) {
		this.idStrets = idStrets;
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
	 * @return the idCounties
	 */
	public Counties getIdCounties() {
		return idCounties;
	}
	
	/**
	 * @return the idLocality
	 */
	public Locality getIdLocality() {
		return idLocality;
	}
	
	/**
	 * @return the idPostoffice
	 */
	public PostOffice getIdPostoffice() {
		return idPostoffice;
	}
	
	/**
	 * @return the idStrets
	 */
	public Streets getIdStrets() {
		return idStrets;
	}
}
