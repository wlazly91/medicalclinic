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
 * Klasa reprezentuj¹ca tabelê Province
 * (województwo)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "PROVINCE")
public class Province implements ObjectDB {

	private static final long serialVersionUID = -1841442360821236465L;

	@Id
	@Column(name = "ID_PROVINCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDPROVINCE") 
	@SequenceGenerator(name="IDPROVINCE", sequenceName = "IDPROVINCE", allocationSize=1)
	private int idProvince;
	
	@Column(name = "NAME")
	private String name;
	
	public Province() {}
	
	public Province(String nameN) {
		this.name = nameN;
	}
	/**
	 * @param idProvince the idProvince to set
	 */
	public void setIdProvince(int idProvince) {
		this.idProvince = idProvince;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the idProvince
	 */
	public int getIdProvince() {
		return idProvince;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
		
}
