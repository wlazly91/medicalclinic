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
 * Klasa reprezentuj¹ca tabelê bazy danych 
 * reprezentuj¹cej choroby
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "DISEASES")
public class Diseases implements ObjectDB {

	private static final long serialVersionUID = -2155110646790965446L;

	@Id
	@Column(name = "ID_DISEASES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDDISEASES") 
	@SequenceGenerator(name="IDDISEASES", sequenceName = "IDDISEASES", allocationSize=1)
	private int idDiseases;
	
	@Column(name = "NAME")
	private String name;
	
	
	public Diseases() {}
	
	
	public Diseases(String nameN) { 
		this.name = nameN;
	}
	/**
	 * @param idDiseases the idDiseases to set
	 */
	public void setIdDiseases(int idDiseases) {
		this.idDiseases = idDiseases;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the idDiseases
	 */
	public int getIdDiseases() {
		return idDiseases;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}
