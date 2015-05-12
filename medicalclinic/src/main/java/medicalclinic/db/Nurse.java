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
 * Klasa reprezentuj¹ca tabelê nurse
 * (pielêgniarke)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "NURSE")
public class Nurse implements ObjectDB {

	private static final long serialVersionUID = 8681338086538276815L;
	
	@Id
	@Column(name = "ID_NURSE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDNURSE") 
	@SequenceGenerator(name="IDNURSE", sequenceName = "IDNURSE", allocationSize=1)
	private int idNurse;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "POSITION")
	private String position;
	
	
	public Nurse() {}
	
	public Nurse(String nameN, String surnameN, String positionN) {
		this.name = nameN;
		this.surname = surnameN;
		this.position = positionN;
	}
	/**
	 * @param idNurse the idNurse to set
	 */
	public void setIdNurse(int idNurse) {
		this.idNurse = idNurse;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * @return the idNurse
	 */
	public int getIdNurse() {
		return idNurse;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	
	
}
