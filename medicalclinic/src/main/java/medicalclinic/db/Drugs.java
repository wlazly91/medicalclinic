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
 * Klasa reprezentuj¹ca tabelê drugs
 * (Lekarstwa)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "DRUGS")
public class Drugs implements ObjectDB {

	private static final long serialVersionUID = -5146212587087433084L;
	
	@Id
	@Column(name = "ID_DRUGS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDDRUGS") 
	@SequenceGenerator(name="IDDRUGS", sequenceName = "IDDRUGS", allocationSize=1)
	private int idDrugs;
	
	@Column(name = "NAME")
	private String name;
	
	/**
	 * @param idDrugs the idDrugs to set
	 */
	public void setIdDrugs(int idDrugs) {
		this.idDrugs = idDrugs;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the idDrugs
	 */
	public int getIdDrugs() {
		return idDrugs;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}
