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
 * Klasa reprezentuj¹ca tabele HeathCheck
 * (badania)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "HEALTH_CHECK")
public class HealthCheck implements ObjectDB {

	private static final long serialVersionUID = 4736086748182759357L;
	
	@Id
	@Column(name = "ID_HEALTH")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDHEALTHCHECK") 
	@SequenceGenerator(name="IDHEALTHCHECK", sequenceName = "IDHEALTHCHECK", allocationSize=1)
	private int idHealth;
	
	@Column(name = "NAME")
	private String name;
	
	
	public HealthCheck() {}
	
	public HealthCheck(String nameN) {
		this.name = nameN;
	}
	/**
	 * @param idHealth the idHealth to set
	 */
	public void setIdHealth(int idHealth) {
		this.idHealth = idHealth;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the idHealth
	 */
	public int getIdHealth() {
		return idHealth;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
