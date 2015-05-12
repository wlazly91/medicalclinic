package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Klasa reprezentuj¹ca innych pracowników
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "OTHER_EMPLOYEE")
public class OtherEmployee implements ObjectDB
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3126141925808569369L;

	@Id
	@Column(name = "ID_EMPLOYEE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDOTHEREMPLOYEE") 
	@SequenceGenerator(name="IDOTHEREMPLOYEE", sequenceName = "IDOTHEREMPLOYEE", allocationSize=1)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "POSITION")
	private String position;
	
	
	public OtherEmployee() {}
	
	public OtherEmployee(String nameN, String surnameN, String positionN) {
		this.name = nameN;
		this.surname = surnameN;
		this.position = positionN;
	}
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
