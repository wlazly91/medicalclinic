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
 * Klasa reprezentuj¹ca strukturê tabeli COUNTIES
 * reprezentuj¹ca powiaty
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "COUNTIES")
public class Counties implements ObjectDB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6092977881137677006L;
	
	@Id
	@Column(name = "ID_COUNTIES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDCOUNTIES") 
	@SequenceGenerator(name="IDCOUNTIES", sequenceName = "IDCOUNTIES", allocationSize=1)
	private int id;
	
	@Column(name = "ID_PROVICE")
	private int idProvice;
	
	@Column(name = "NAME")
	private String name;
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param idProvice the idProvice to set
	 */
	public void setIdProvice(int idProvice) {
		this.idProvice = idProvice;
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
	 * @return the idProvice
	 */
	public int getIdProvice() {
		return idProvice;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}
